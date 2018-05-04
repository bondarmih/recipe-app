package ru.bondarmih.recipeparser.service.parser.impl;

import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import ru.bondarmih.recipeparser.config.PathsConfiguration;
import ru.bondarmih.recipeparser.data.domain.ImageDescriptor;
import ru.bondarmih.recipeparser.data.repository.ImagesRepository;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by bondarm on 27.04.18.
 */

@Component
public class ImgPersister {

    private WebClient webClient;

    private String basePath;

    private ImagesRepository imagesRepository;

    public ImgPersister(
            PathsConfiguration pathsConfiguration,
            ImagesRepository imagesRepository,
            WebClient mainWebClient
    ) {
        this.basePath = pathsConfiguration.getBasePath();
        this.imagesRepository = imagesRepository;
        this.webClient = mainWebClient;
    }

    @Async("threadPoolExecutor")
    public void loadAndPersist(String path) {
        if (path == null || path.isEmpty()) {
            return;
        }
        ImageDescriptor descriptor = new ImageDescriptor();
        descriptor.setPath(path);
        System.out.println(path +  path.length());

        try(InputStream openStream = new URL(path).openStream()) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] data = new byte[4096];
            int n;
            while ( (n = openStream.read(data)) > 0 ) {
                bos.write(data, 0, n);
            }
            descriptor.setData(bos.toByteArray());
            openStream.close();
        } catch (Exception e) {
            System.out.println(path);
            e.printStackTrace();
        }

//        ClientResponse response = webClient.get()
//                .uri(path.replace(basePath, ""))
//                .accept(MediaType.IMAGE_JPEG)
//                .exchange()
//                .block();
//        Byte[] data = response.bodyToMono(Byte[].class)
//                .block();

        imagesRepository.save(descriptor);
    }
}
