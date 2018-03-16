package com.demo;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.sql.Date;

/**
 * @version 2018-3-14 16:19
 * @autor Administrator
 */
public class ClientGlobalDemo {
    public static void main(String[] args) throws Exception{
        ClientGlobal.initByProperties("fastdfs-client.properties");
        System.out.println(ClientGlobal.configInfo());

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer,null);
        NameValuePair[] pairs = new NameValuePair[1];
        NameValuePair pair = new NameValuePair();
        pair.setName("test");
        pair.setValue("hello");
        pairs[0] = pair;
        String[] strs = storageClient.upload_file("D://jj.png","png",pairs);
        for(String str : strs){
            System.out.println(str);
        }
        System.out.println(System.currentTimeMillis());
        int ts = (int)(System.currentTimeMillis()/1000);
        String token = ProtoCommon.getToken(strs[1],ts,"FastDFS1234567890");
        System.out.println(token);
        System.out.println(ts);

    }

}
