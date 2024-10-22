package com.cinema.config;

import com.cinema.domain.Notice;
import com.cinema.dto.movie.NoticeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // 매칭 전략을 STRICT로 설정
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // 속성 간 명시적 매핑 정의
        modelMapper.addMappings(new PropertyMap<Notice, NoticeDTO>() {
            @Override
            protected void configure() {
                map().setNNum(source.getNNum()); // nNum 매핑
                map().setNTitle(source.getNTitle()); // nTitle 매핑
                map().setNContent(source.getNContent()); // nContent 매핑
                map().setNDate(source.getNDate()); // nDate 매핑
            }
        });

        return modelMapper;
    }
}
