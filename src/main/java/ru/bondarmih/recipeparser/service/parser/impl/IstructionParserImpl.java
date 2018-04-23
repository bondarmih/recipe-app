package ru.bondarmih.recipeparser.service.parser.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.data.domain.Instruction;
import ru.bondarmih.recipeparser.service.parser.InstructionParser;

import java.util.Optional;

/**
 * Created by bondarm on 20.04.18.
 */

@Component
public class IstructionParserImpl implements InstructionParser {

    @Override
    public Instruction parse(Element element) {
        Instruction instruction = new Instruction();
        instruction.setText(
                Optional.ofNullable(Selector.selectFirst(".instruction_description", element))
                    .map(Element::text)
                .orElse(null)
        );
        instruction.setImgPath(
                Optional.ofNullable(Selector.selectFirst(".instruction_image>a", element))
                    .map(e -> e.attr("src"))
                    .orElse(null)
        );
        return instruction;
    }

}
