/**
 * dataofx.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package p.zh.assembly.middleware.auth.config.mvc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.ConverterRegistry;
import p.zh.assembly.middleware.common.em.standard.BaseEnum;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * mvc jackson配置
 * </p>
 *
 * @author p.zh
 * @since 2022-08-03
 */
@Configuration
@Slf4j
public class JacksonAndConvertConfig {

    private static final String DATE_TIME_FORMAT = "yyyy-M-d H:m:s";

    private static final String DATE_TIME_FORMAT_FULL = "yyyy-MM-dd H:m:s";

    private static final String DATE_TIME_FORMAT_STANDARD = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_TIME_FORMAT_WITHOUT_SECONDS = "yyyy-M-d H:m";

    private static final String TIME_FORMAT = "H:m:s";

    private static final String DATE_FORMAT = "yyyy-M-d";

    private static final String DATE_FORMAT_FULL = "yyyy-MM-dd";

    private final LocalDateTimeDeserializer classicDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

    private final LocalDateTimeDeserializer withoutSecondsDeserializer = new LocalDateTimeDeserializer(
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_WITHOUT_SECONDS));


    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STANDARD));
    }

    @Bean
    public JsonDeserializer<LocalDateTime> localDateTimeDeserializer() {

        return new JsonDeserializer<LocalDateTime>() {

            @Override
            public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                try {
                    return classicDeserializer.deserialize(jsonParser, deserializationContext);
                } catch (Exception e){
                    try {
                        log.debug(e.getMessage());
                        return withoutSecondsDeserializer.deserialize(jsonParser, deserializationContext);
                    } catch (Exception e1) {
                        log.debug(e1.getMessage());
                        return LocalDate.parse(jsonParser.getText().trim(), DateTimeFormatter.ofPattern(DATE_FORMAT))
                                        .atStartOfDay();
                    }

                }
            }
        };
    }

    @Bean
    public LocalDateSerializer localDateSerializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT_FULL));
    }

    @Bean
    public LocalDateDeserializer localDateDeserializer() {
        return new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeSerializer())
                .deserializerByType(LocalDateTime.class, localDateTimeDeserializer())
                .serializerByType(LocalDate.class, localDateSerializer())
                .deserializerByType(LocalDate.class, localDateDeserializer());
    }

    @Bean
    public ConverterFactory<String, BaseEnum> baseEnumConverterFactory() {
        return new BaseEnumConverterFactory();
    }

    @Bean
    @ConditionalOnWebApplication
    public ConverterRegistry initConverter(ConverterRegistry registry) {
        registry.addConverterFactory(baseEnumConverterFactory());
        return registry;
    }
}