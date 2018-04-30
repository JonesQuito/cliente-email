package com.roboquito.testes;

import com.roboquito.email.cliente.service.Arquivo;
import com.roboquito.email.cliente.service.CriptografiaAES;
import com.roboquito.email.cliente.service.CriptografiaRSA;
import java.net.Socket;

//import com.roboquito.email.cliente.model.Pacote;
//import com.roboquito.email.cliente.model.ServerMethods;
import com.roboquito.email.cliente.service.Util;
import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.PrivateKey;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cliente {

    public static void fixKeyLength() {
        String errorString = "Failed manually overriding key-length permissions.";
        int newMaxKeyLength;
        try {
            if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
                Class c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
                Constructor con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissionCollection = con.newInstance();
                Field f = c.getDeclaredField("all_allowed");
                f.setAccessible(true);
                f.setBoolean(allPermissionCollection, true);

                c = Class.forName("javax.crypto.CryptoPermissions");
                con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissions = con.newInstance();
                f = c.getDeclaredField("perms");
                f.setAccessible(true);
                ((Map) f.get(allPermissions)).put("*", allPermissionCollection);

                c = Class.forName("javax.crypto.JceSecurityManager");
                f = c.getDeclaredField("defaultPolicy");
                f.setAccessible(true);
                Field mf = Field.class.getDeclaredField("modifiers");
                mf.setAccessible(true);
                mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                f.set(null, allPermissions);

                newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
            }
        } catch (Exception e) {
            throw new RuntimeException(errorString, e);
        }
        if (newMaxKeyLength < 256) {
            throw new RuntimeException(errorString); // hack failed
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        //fixKeyLength();

        Pacote pacote = new Pacote();

        pacote.setMetodo(ServerMethods.GET_ALL_OBJECTS);

        Socket socket = new Socket("127.0.0.1", 5000);
        Util.enviarObjeto(pacote, socket.getOutputStream());

        PrivateKey privatekey = (PrivateKey) Arquivo.lerObject("C:/keys/private.key");

        pacote = (Pacote) Util.lerObjecto(socket.getInputStream());

        byte[] skey = CriptografiaRSA.decriptografa(pacote.getChaveSimetrica(), privatekey);

        SecretKeySpec skeyspec = new SecretKeySpec(skey, "AES");

        System.out.println("Tamanho da chave: " + skey.length);
        
        if (pacote != null) {
            System.out.println("Chave privada: " + privatekey.getEncoded());
            System.out.println("Remetente: " + pacote.getRemetente());
            System.out.println("Destinatário: " + pacote.getDestinatario());
            System.out.println("MENSAGEM RECEBIDA DO SERVIDOR: " + new String(CriptografiaAES.decriptografar(skeyspec, pacote.getMensagem())));
        }

    }

}
