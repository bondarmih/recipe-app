package ru.bondarmih.recipeparser.service.parser.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.data.domain.Instruction;
import ru.bondarmih.recipeparser.service.parser.InstructionParser;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by bondarm on 20.04.18.
 */

@Component
public class IstructionParserImpl implements InstructionParser {

    @Autowired
    private ImgPersister imgPersister;

    @Override
    public List<Instruction> parse(Element element) {
        Function<Element, Instruction> versionParser = element.hasClass("ver_1") ?
                this::getVersion1Instruction :
                this::getVersion2Instruction;

            return Selector.select("li", element)
                    .stream()
                    .map(versionParser)
                    .collect(Collectors.toList());
    }

    private Instruction getVersion1Instruction(Element element) {
        Instruction instruction = new Instruction();
        instruction.setText(element.text());
        return instruction;
    }

    private Instruction getVersion2Instruction(Element element) {
        Instruction instruction = new Instruction();
        instruction.setText(
                Optional.ofNullable(Selector.selectFirst(".instruction_description", element))
                        .map(Element::text)
                        .orElse(null)
        );
        Optional.ofNullable(Selector.selectFirst(".instruction_image>a", element))
                .map(e -> e.attr("src"))
                .ifPresent(path -> {
                    instruction.setImgPath(path);
                    imgPersister.loadAndPersist(path);
                });
        return instruction;
    }

}
