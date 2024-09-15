package com.plenigo.task.services.plenigosdk;

import com.plenigo.task.services.webclient.GenericWebClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PlenigoSdkService {

    private GenericWebClient genericWebClient;

    public <T> List<T> getList(Class<T> responseType, String uri, int totalSize, int pagingSize) {
        List<T> list = new LinkedList<>();

        while(list.size() < totalSize && list.size() % pagingSize == 0){
            List<T> additions = genericWebClient.getList(responseType, uri, pagingSize, null).block();
            list.addAll(additions);
        }

        return list;
    }

    public <T> T get(Class<T> responseType, String uri, int pagingSize) {
        return genericWebClient.get(responseType, uri, pagingSize).block();
    }

    public <T> List<T> searchList(Class<T> responseType, String uri, int totalSize, int pagingSize, Map<String, String> queryParams) {

        List<T> list = new LinkedList<>();
        List<T> additions = new LinkedList<>();

        while(list.size() < totalSize && list.size() % pagingSize == 0 && additions != null){
            additions = genericWebClient.getList(responseType, uri, pagingSize, queryParams).block();
            if(additions != null){
                list.addAll(additions);
            }
        }

        return list;
    }
}
