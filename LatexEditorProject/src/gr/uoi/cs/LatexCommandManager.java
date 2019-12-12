package gr.uoi.cs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.model.LatexCommand;

public class LatexCommandManager {
	private static final String COMMANDS_FILE = "/gr/uoi/cs/resource/latex_commands.xml";
	private Map<String, List<LatexCommand>> commands;

	public LatexCommandManager() {
		commands = new HashMap<String, List<LatexCommand>>();
		loadLatexCommands();

	}

	private void loadLatexCommands() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(getClass().getResourceAsStream(COMMANDS_FILE));
			doc.getDocumentElement().normalize();
			NodeList categoryNodeList = doc.getElementsByTagName("category");
			for (int temp = 0; temp < categoryNodeList.getLength(); temp++) {
				Node categoryNode = categoryNodeList.item(temp);
				if (categoryNode.getNodeType() == Node.ELEMENT_NODE) {
					Element categoryElement = (Element) categoryNode;
					String categoryName = categoryElement.getAttribute("name");
					List<LatexCommand> commandsOfThisCategory = parseCategoryFromElement(categoryElement);
					commands.put(categoryName, commandsOfThisCategory);
				}
			}
		} catch (SAXException | ParserConfigurationException | IOException e) {
			System.err.println("Error reading XML file with commands. Path: " + COMMANDS_FILE);
			e.printStackTrace();
		}
		commands = Collections.unmodifiableMap(commands);
	}

	private List<LatexCommand> parseCategoryFromElement(Element categoryElement) {
		List<LatexCommand> commandList = new ArrayList<LatexCommand>();
		NodeList commandNodeList = categoryElement.getElementsByTagName("command");
		for (int temp = 0; temp < commandNodeList.getLength(); temp++) {
			Node commandNode = commandNodeList.item(temp);
			if (commandNode.getNodeType() == Node.ELEMENT_NODE) {
				Element commandElement = (Element) commandNode;
				LatexCommand latexCommand = buildCommandFromElement(commandElement);
				commandList.add(latexCommand);
			}
		}
		return commandList;
	}

	private LatexCommand buildCommandFromElement(Element element) {
		String name = element.getAttribute("name");
		String category = element.getAttribute("category");
		category = category == null ? "" : category;
		String content = getTagValue(element, "content").replaceAll("\t", "").replaceAll("\\\\n",
				System.lineSeparator());
		String allowedTypes = getTagValue(element, "allowed_documents");
		String disallowedTypes = getTagValue(element, "disallowed_documents");

		return new LatexCommand(name, content, allowedTypes, disallowedTypes, category);
	}

	private String getTagValue(Element element, String tag) {
		NodeList nodeList = element.getElementsByTagName(tag);
		if (nodeList != null) {
			Node node = nodeList.item(0);
			if (node != null)
				return node.getTextContent().trim();
		}
		return "";
	}

	public Map<String, List<LatexCommand>> getCommandsForDocumentType(DocumentType type) {
		Map<String, List<LatexCommand>> result = new HashMap<>();
		for (String category : commands.keySet()) {
			List<LatexCommand> categoryCommands = commands.get(category);
			List<LatexCommand> allowedCommands = categoryCommands.stream().filter(c -> c.allowsType(type))
					.collect(Collectors.toList());
			if (!allowedCommands.isEmpty()) {
				result.put(category, allowedCommands);
			}
		}
		return result;
	}
}
