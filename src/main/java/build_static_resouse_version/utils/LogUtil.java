package build_static_resouse_version.utils;

import org.apache.maven.plugin.logging.Log;

/**
 * Created by lmm on 2017/5/9.
 */
public class LogUtil {
    public static Log log = null;

    public static void init(Log _log){
        log = _log;
    }

    public static void info(String msg){
        if(log==null){
            System.out.println(msg);
            return;
        }
        log.info(msg);
    }
}
