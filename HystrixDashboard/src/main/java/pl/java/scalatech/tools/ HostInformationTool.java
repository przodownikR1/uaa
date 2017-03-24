package pl.java.scalatech.tools;

import static java.net.InetAddress.getLocalHost;

import java.net.UnknownHostException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE) 
public final class HostInformationTool  {
        
    @SneakyThrows(UnknownHostException.class)
    public static String getIp() {        
            return getLocalHost().getHostAddress();               
    }
   
}
