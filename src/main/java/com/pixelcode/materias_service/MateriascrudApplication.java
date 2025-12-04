package com.pixelcode.materias_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Microservicio de Materias
 * Gestiona el catálogo de materias/asignaturas y sus relaciones con programas educativos
 * 
 * Migrado a:
 * - Clever Cloud MySQL (base de datos compartida)
 * - Eureka Service Discovery
 * - API Gateway compatible
 * 
 * @version 2.0 - MySQL & Eureka Integration with Programa Relationships
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MateriascrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MateriascrudApplication.class, args);
		
		System.out.println("\n╔════════════════════════════════════════════════════════════╗");
		System.out.println("║                                                            ║");
		System.out.println("║          MICROSERVICIO DE MATERIAS INICIADO                ║");
		System.out.println("║                                                            ║");
		System.out.println("║  Servicio: materias-service                                ║");
		System.out.println("║  Puerto: 8084                                              ║");
		System.out.println("║  Database: Clever Cloud MySQL                              ║");
		System.out.println("║  Eureka: http://localhost:8761                             ║");
		System.out.println("║                                                            ║");
		System.out.println("║  === MATERIAS ===                                          ║");
		System.out.println("║  API Base: http://localhost:8084/api/materias              ║");
		System.out.println("║  • GET    /api/materias              [Listar todas]        ║");
		System.out.println("║  • GET    /api/materias/activas      [Solo activas]        ║");
		System.out.println("║  • GET    /api/materias/{id}         [Obtener por ID]      ║");
		System.out.println("║  • POST   /api/materias              [Crear materia]       ║");
		System.out.println("║  • PUT    /api/materias/{id}         [Actualizar]          ║");
		System.out.println("║  • DELETE /api/materias/{id}         [Eliminar lógico]     ║");
		System.out.println("║  • PATCH  /api/materias/{id}/reactivar [Reactivar]        ║");
		System.out.println("║                                                            ║");
		System.out.println("║  === MATERIA-PROGRAMA (Relaciones Many-to-Many) ===       ║");
		System.out.println("║  API Base: http://localhost:8084/api/materias-programas    ║");
		System.out.println("║  • POST   /api/materias-programas    [Asignar materia]     ║");
		System.out.println("║  • GET    /api/materias-programas    [Todas relaciones]    ║");
		System.out.println("║  • GET    /api/materias-programas/activas                  ║");
		System.out.println("║  • GET    /api/materias-programas/programa/{id}            ║");
		System.out.println("║           [Materias de un programa]                        ║");
		System.out.println("║  • GET    /api/materias-programas/materia/{id}             ║");
		System.out.println("║           [Programas de una materia]                       ║");
		System.out.println("║  • DELETE /api/materias-programas/materia/{m}/programa/{p} ║");
		System.out.println("║                                                            ║");
		System.out.println("║  Vía API Gateway:                                          ║");
		System.out.println("║  http://localhost:8080/materias-service/api/...            ║");
		System.out.println("║                                                            ║");
		System.out.println("║  📊 15 Endpoints Total - Many-to-Many Support ✓            ║");
		System.out.println("║  🎓 UTEQ - Sistema de Asistencias 2025                     ║");
		System.out.println("║                                                            ║");
		System.out.println("╚════════════════════════════════════════════════════════════╝\n");
	}

}