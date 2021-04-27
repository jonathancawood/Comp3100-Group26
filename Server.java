/*
ds-System.xml
*/

public class Server {
    private int core;
    private int mem;
    private int disk;
    //private int bootTime;
    private String type;
    private int limit; // shows how many servers of each type is avable at ds-server 

    public Server(int _core, int _mem, int _disk, int _bootTime, int _limit, String _type){
        //setters methods
        setServerCoreCount(_core);
        setServerMemory(_mem);
        setServerDisk(_disk);
        setServerLimit(_limit);
        setServerType(_type);
    }

    public void setServerCoreCount(int _coreCount){
        this.core = _coreCount;
    }

    public void setServerMemory(int _mem){
        this.mem = _mem;
    }

    public void setServerDisk(int _disk){
        this.disk = _disk;
    }

    public void setServerType(String _type){
        this.type = _type;
    }

    public void setServerLimit(int _limit){
        this.limit = _limit;
    }

    public int getServerCoreCount(){
        return this.core;
    }

    public int getServerMemory(){
        return this.mem;
    }

    public int getServerDisk(){
        return this.disk;
    }

    public String getServerType(){
        return this.type;
    }

    public int getServerLimit(){
        return this.limit;
    }


}