package org.example.aplication;

import org.example.ImplementacionRepository.JpaHotelRepository;
import org.example.aplication.service.HotelService;
import org.example.aplication.service.HotelServiceImpl;
import org.example.domain.Hotel;
import org.example.interfaces.HotelRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HotelRepository hotelRepository = new JpaHotelRepository();
        HotelService hotelService = new HotelServiceImpl(hotelRepository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar hotel");
            System.out.println("2. Listar hoteles");
            System.out.println("3. Obtener hotel por ID");
            System.out.println("4. Actualizar hotel");
            System.out.println("5. Eliminar hotel");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del hotel: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese dirección del hotel: ");
                    String direccion = scanner.nextLine();
                    Hotel hotel = new Hotel(nombre, direccion);
                    hotelService.agregarHotel(hotel);
                    System.out.println("Hotel agregado exitosamente.");
                    break;

                case 2:
                    System.out.println("Lista de hoteles:");
                    for (Hotel h : hotelService.listarHoteles()) {
                        System.out.println(h);
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del hotel: ");
                    int id = scanner.nextInt();
                    Hotel hotelObtenido = hotelService.obtenerHotel(id);
                    if (hotelObtenido != null) {
                        System.out.println(hotelObtenido);
                    } else {
                        System.out.println("Hotel no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID del hotel que desea actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    Hotel hotelActualizar = hotelService.obtenerHotel(idActualizar);
                    if (hotelActualizar != null) {
                        System.out.print("Ingrese nuevo nombre del hotel: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese nueva dirección del hotel: ");
                        String nuevaDireccion = scanner.nextLine();
                        hotelActualizar.setNombre(nuevoNombre);
                        hotelActualizar.setDireccion(nuevaDireccion);
                        hotelService.actualizarHotel(hotelActualizar);
                        System.out.println("Hotel actualizado exitosamente.");
                    } else {
                        System.out.println("Hotel no encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el ID del hotel que desea eliminar: ");
                    int idEliminar = scanner.nextInt();
                    hotelService.eliminarHotel(idEliminar);
                    System.out.println("Hotel eliminado exitosamente.");
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
