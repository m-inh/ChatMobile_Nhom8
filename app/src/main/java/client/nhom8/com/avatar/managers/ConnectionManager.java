package client.nhom8.com.avatar.managers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author KN_SV023
 */
public class ConnectionManager {
    private static ConnectionManager _sharePointer;
    private Socket soc;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private ConnectionManager() throws IOException {

    }
    public static ConnectionManager getIntance() throws IOException{
        if(_sharePointer == null){
            _sharePointer = new ConnectionManager();
        }
        return _sharePointer;
    }

    public Socket getSoc() {
        return soc;
    }

    public void setSoc(Socket soc) {
        this.soc = soc;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

}
