/*
 * Decompiled with CFR 0.152.
 */
package steuerung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import steuerung.Setting;

public class StaXParser {
    static final String AKTUALISIERUNGSZEIT = "refresh";
    static final String ARBEITSZEIT = "worktime";
    static final String SETTINGS = "settings";

    public List<Setting> readConfig(String configFile) {
        ArrayList<Setting> settings = new ArrayList<Setting>();
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            FileInputStream is = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(is);
            Setting setting = null;
            while (eventReader.hasNext()) {
                EndElement endElement;
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart() == SETTINGS) {
                        setting = new Setting();
                    }
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(AKTUALISIERUNGSZEIT)) {
                            event = eventReader.nextEvent();
                            setting.setRefresh(event.asCharacters().getData());
                            continue;
                        }
                        if (event.asStartElement().getName().getLocalPart().equals(ARBEITSZEIT)) {
                            event = eventReader.nextEvent();
                            setting.setWorktime(event.asCharacters().getData());
                            continue;
                        }
                    }
                }
                if (!event.isEndElement() || (endElement = event.asEndElement()).getName().getLocalPart() != SETTINGS) continue;
                settings.add(setting);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden!");
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return settings;
    }
}

