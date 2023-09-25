package com.example.kodillaenrollment.service;

import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.domain.WsdcAutocompleteResultDto;
import com.example.kodillaenrollment.domain.WsdcFindResultDto;
import com.example.kodillaenrollment.domain.WsdcInfoDto;
import com.example.kodillaenrollment.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WsdcService {

    private static final Map<String, String> LEVEL_MAPPINGS = Map.of(
            "NOV", "Novice",
            "NEW", "Newcomer",
            "INT", "Intermediate",
            "ADV", "Advanced",
            "ALS", "All-Stars",
            "CHMP", "Champion"
    );

    @Autowired
    private StudentRepository studentRepository;

    private final WebClient webClient = WebClient.create("https://points.worldsdc.com/lookup2020");


    public Optional<WsdcInfoDto> fetchStudentInfo(Long studentId) {
        String name = getStudentName(studentId);
        List<WsdcAutocompleteResultDto> nameList = getWsdcAutocompleteResults(name);
        if (nameList.isEmpty()) {
            return Optional.empty();
        } else {
            Long number = nameList.get(0).getWscid();
            WsdcFindResultDto resultDto = getFindResult(number);
            WsdcInfoDto infoDto = new WsdcInfoDto(resultDto.getDominantRole(), LEVEL_MAPPINGS.get(resultDto.getAllowedLevel()));
            return Optional.of(infoDto);
        }
    }

    private String getStudentName(Long studentId) {
        Optional<Student> selected = studentRepository.findById(studentId);
        return selected.get().getFirstname() + " " + selected.get().getLastname();
    }

    private List<WsdcAutocompleteResultDto> getWsdcAutocompleteResults(String name) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/autocomplete")
                        .queryParam("q", name)
                        .build())
                .retrieve()
                .toEntityList(WsdcAutocompleteResultDto.class)
                .block().getBody();
    }

    private WsdcFindResultDto getFindResult(Long number) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/find")
                        .queryParam("num", number)
                        .build())
                .retrieve()
                .toEntity(WsdcFindResultDto.class)
                .block().getBody();
    }

}
