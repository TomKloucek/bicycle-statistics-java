package czm.write_to_file;

import czm.statistics_type.AStatistics;
import czm.helpers.Helpers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteToXML {
    private WriteToXML() {}
    static Logger logger = Logger.getLogger(WriteToXML.class.getName());

    public static boolean writeToFile(List<AStatistics> statistics) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("report");
            doc.appendChild(rootElement);

            Element createdElement = doc.createElement("created");
            createdElement.appendChild(doc.createTextNode(Helpers.getCurrentTime()));

            Element statisticsResultElement = doc.createElement("statisticsResults");
            rootElement.appendChild(statisticsResultElement);

            for (AStatistics stats : statistics) {
                Element statisticElement = doc.createElement("statistics");

                Element nameElement = doc.createElement("name");
                Element valueElement = doc.createElement("value");
                nameElement.appendChild(doc.createTextNode(stats.getName()));
                valueElement.appendChild(doc.createTextNode(String.valueOf(stats.getValue())));
                statisticElement.appendChild(nameElement);
                statisticElement.appendChild(valueElement);
                statisticsResultElement.appendChild(statisticElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml_report.xml"));
            transformer.transform(source, result);
            return true;

        } catch (ParserConfigurationException | TransformerException pce) {
            logger.log(Level.SEVERE, pce.getMessage());
            return false;
        }

    }
}
