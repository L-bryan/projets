/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.esi.exception;

/**
 *
 * @author dylanlevymorini
 */
public class NetworkException extends Exception {
    
    
    /**
     * Creates a new instance of <code>BusinessException</code> without detail message.
     */
    public NetworkException() {
    }


    /**
     * Constructs an instance of <code>BusinessException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NetworkException(String msg) {
        super(msg);
    }
    
}
