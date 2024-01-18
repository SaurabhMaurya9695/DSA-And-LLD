// Solution 1:
// class LoggerService {
//     private static LoggerService instance = new LoggerService();

//     private LoggerService() {
//     }

//     public static LoggerService getInstance() {
//         return instance;
//     }

//     public void log(String str) {
//         System.out.println(str);
//     }
// }

// Solution 2: Lazy Propogation -> jb Hme chhaiye tb hi hum sirf object ko create karengy 
// class LoggerService {
//     private static LoggerService instance;

//     private LoggerService() {

//     }

//     public static LoggerService getInstance() {
//         if (instance == null) {
//             instance = new LoggerService();
//         }

//         return instance;
//     }

//     public void log(String str) {
//         System.out.println(str);
//         System.out.println(this);
//     }
// }

// Solution 3: Synchronized Method -> we can get two threads so protect this from thread made block Synchronized
// class LoggerService {
//     private static LoggerService instance;

//     private LoggerService() {

//     }

//     public synchronized static LoggerService getInstance() {
//         if (instance == null) {
//             instance = new LoggerService();
//         }

//         return instance;
//     }

//     public void log(String str) {
//         System.out.println(str);
//         System.out.println(this);
//     }
// }

// Solution 4: Double Locking: Synchronized Block
class LoggerService {
    private static LoggerService instance;

    private LoggerService() {

    }

    public static LoggerService getInstance() {
        // double locking 
        if (instance == null) {
            synchronized (LoggerService.class) {
                if (instance == null) {
                    instance = new LoggerService();
                }
            }
        }

        return instance;
    }

    public void log(String str) {
        System.out.println(str);
        System.out.println(this);
    }
}

class CustomThread extends Thread {
    @Override
    public void run() {
        LoggerService log = LoggerService.getInstance();
        log.log("New Thread Log Created");
    }
}

enum LoggerServiceEnum {
    INSTANCE;

    public void log(String str) {
        System.out.println(str);
    }
}

class Client {
    public static void multithreaded() {
        CustomThread t1 = new CustomThread();
        t1.start();

        CustomThread t2 = new CustomThread();
        t2.start();
    }

    public static void loggerEnum() {
        LoggerServiceEnum logger = LoggerServiceEnum.INSTANCE;
        logger.log("CPU Utilization Over 75%");
        logger.log("RAM Under 25%");
    }

    public static void main(String[] args) {
        LoggerService log1 = LoggerService.getInstance();
        log1.log("CPU utilization over 50%");

        LoggerService log2 = LoggerService.getInstance();
        log2.log("RAM under 25% left");

        System.out.println(log1 == log2); // true

        // multithreaded();
        // loggerEnum();
    }
}
