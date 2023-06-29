/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FuncionesVarias;

import Functions.Habitacion;
import Hashtable.Client;
import javax.swing.JOptionPane;
import static proyecto.pkg2.Main.hash;
import static proyecto.pkg2.Main.historial;
import static proyecto.pkg2.Main.rooms;
import static proyecto.pkg2.Main.reservas;

/**
 *
 * @author alexandralecuna
 */
public class Funciones {

    public void checkIn(Client cliente) {
        
        /**
        * Metodo que asigna la habitación a un cliente que se encuentra reservado
        */

        if (reservas.checkClient(reservas.getRoot(), cliente)) {
            int hab = asignarHab(cliente);
            if (hab != -1) {
                cliente.setRoomNum(hab);
                reservas.deleteNodo(cliente, reservas.getRoot(), null);
                hash.insertInHashtable(cliente);
                hash.printTable();
            } else {
                JOptionPane.showMessageDialog(null, "El hotel no tiene habitaciones " + cliente.getTipoHab() + " disponibles.");
            }
        } else {
            System.out.println("Error. El cliente no posee una reservacion en el Hotel Oasis.");
        }
    }

    public void checkOut(Client cliente) {
        
        /**
        * Metodo que hace referencia al momento de salida del huesped del hotel, y desocupa la habitación
        */
        if (hash.checkClient(cliente)) {
//            freeRoom(cliente);
            hash.removeHospedado(cliente.getName(), cliente.getLastName());
//            reservas.insertNodo(reservas.getRoot(), cliente);
            historial.insertarCliente(historial.getRoot(), cliente);
            freeRoom(cliente);

        } else {
            JOptionPane.showMessageDialog(null, "Error. El cliente"+cliente.getName()+" "+cliente.getLastName()+" no se encuentra hospedado en el Hotel Oasis.");
        }
    }

    public int asignarHab(Client cliente) {
        
        /**
        * Metodo que busca las habitaciones disponibles del hotel para asignarla a los huespedes
        */
        
        String roomType = cliente.getTipoHab();
        for (int i = 0; i < rooms.getSize(); i++) {
            Habitacion room = (Habitacion) rooms.getDato(i).getElement();
            if (room.isFree()) {
                if (roomType.equals(room.getTipo_hab())) {
                    room.setFree(false);
                    return room.getNum_hab();
                }
            }
        }
        return -1;
    }

    
    public void freeRoom(Client cliente){
        /**
        * Metodo que determina la disponibilidad de las habitaciones del hotel
        */
        
        int roomNum = cliente.getRoomNum();
        Habitacion room = (Habitacion) rooms.getDato(roomNum-1).getElement();
        room.setFree(true);
        cliente.setRoomNum(-1);
    }

    
     public boolean containsNumbers (String word){
         
        /**
        * Metodo que valida que un texto contenga unicamente Strings y no números
        */
         
        for (int i = 0; i < word.length(); i++) {
        if (Character.isDigit(word.charAt(i))) {
            return true;
        }
    }
    return false;
    }
     
}
