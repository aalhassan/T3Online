package com.ticCore.restServices;

import com.ticCore.beans.TicState;
import org.apache.log4j.Logger;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * This is the main class for the implementation
 * of MessageBodyReader and MessageBodyWriter
 *
 * @author Mej Al
 */
@Provider
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
public class MessageBodyReaderWriter implements MessageBodyReader<TicState>, MessageBodyWriter<TicState> {
    private Logger logger = Logger.getLogger(this.getClass());
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    public TicState readFrom(Class<TicState> msgClass, Type type, Annotation[] annotations,
                                MediaType mediaType,
                                MultivaluedMap<String, String> stringTicStateMultivaluedMap,
                                InputStream inputStream) throws IOException, WebApplicationException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TicState.class.getPackage().getName());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (TicState) unmarshaller.unmarshal(inputStream); //return object unmarshalled from from inputstream(XML)

        } catch (JAXBException e) {
           logger.trace(e);
        }
        return null;
    }

    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    public long getSize(TicState msg, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    public void writeTo(TicState msg, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> stringObjectMultivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        try {
            if (mediaType.equals(MediaType.APPLICATION_XML_TYPE)) {
                JAXBContext jaxbContext = JAXBContext.newInstance(TicState.class);

                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(msg, outputStream);
            } else {
                outputStream.write(msg.toString().getBytes());

            }
        } catch (Exception e) {
            logger.trace(e);
        }
    }

}

