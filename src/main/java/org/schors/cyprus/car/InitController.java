package org.schors.cyprus.car;

import org.slyrack.telegrambots.ModelAndView;
import org.slyrack.telegrambots.StatefulModelAndView;
import org.slyrack.telegrambots.annotations.Command;
import org.slyrack.telegrambots.annotations.Controller;
import org.slyrack.telegrambots.flags.UpdateType;

@Controller
public class InitController {

    @Command(value = UpdateType.MESSAGE)
    public ModelAndView start() {
        return new StatefulModelAndView("initial", "start");
    }
}
