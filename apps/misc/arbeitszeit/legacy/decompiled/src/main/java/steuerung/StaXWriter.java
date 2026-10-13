/*
 * Decompiled with CFR 0.152.
 */
package steuerung;

import java.io.File;
import java.io.FileOutputStream;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;

public class StaXWriter {
    private String configFile;
    private static String ROOT_ELEMENT = "settings";

    public void setFile(String configFile) {
        this.configFile = configFile;
    }

    public void createConfig(String refresh, String worktime) throws Exception {
        new File(this.configFile.replace("config.xml", "")).mkdirs();
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(this.configFile));
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);
        StartElement confStartElement = eventFactory.createStartElement("", "", ROOT_ELEMENT);
        eventWriter.add(confStartElement);
        this.createNode(eventWriter, "refresh", refresh);
        this.createNode(eventWriter, "worktime", worktime);
        eventWriter.add(eventFactory.createEndElement("", "", ROOT_ELEMENT));
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }

    public void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        DTD end = eventFactory.createDTD("\n");
        DTD tab = eventFactory.createDTD("\t");
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}

