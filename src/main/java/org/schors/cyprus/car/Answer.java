package org.schors.cyprus.car;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Answer {
    private String question;
    private List<String> answers;
}
