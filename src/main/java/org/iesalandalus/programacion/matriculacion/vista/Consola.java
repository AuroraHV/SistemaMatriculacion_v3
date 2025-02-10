package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.matriculacion.dominio.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

//1-Clase Consola
public class Consola {

    //2-Constructor de utilidades
    private Consola() {
    }

    //3-Muestra menú de opciones
    public static void mostrarMenu() {
        System.out.println("Opciones del menú:");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    //4-Elige opción
    public static Opcion elegirOpcion() {
        int opcion;
        do {
            System.out.print("Introduce el número de la opción: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Opcion.values().length);
        return Opcion.values()[opcion];
    }

    //ALUMNO
    public static Alumno leerAlumno() {
        final Alumno ALUMNO_GUIA = new Alumno("Carlos", "12345678Z", "carlos.perez@gmail.com", "612345678",
                LocalDate.parse("15/03/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        String nombre = null;
        String dni = null;
        String correo = null;
        String telefono = null;
        LocalDate fechaNacimiento;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Validación del nombre
        boolean valido = false;
        do {
            System.out.print("Introduce el nombre del alumno: ");
            try {
                nombre = Entrada.cadena();
                if (nombre == null || nombre.isBlank()) {
                    throw new IllegalArgumentException("ERROR: El nombre no puede ser nulo o vacío.");
                }
                Alumno alumnoNombre = new Alumno(nombre,ALUMNO_GUIA.getDni(), ALUMNO_GUIA.getCorreo(), ALUMNO_GUIA.getTelefono(), ALUMNO_GUIA.getFechaNacimiento());
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validación del DNI
        valido = false;
        do {
            System.out.print("Introduce el DNI del alumno: ");
            try {
                dni = Entrada.cadena();
                if (dni == null || dni.isBlank()) {
                    throw new IllegalArgumentException("ERROR: El DNI no puede ser nulo o vacío.");
                }
                Alumno alumnoDni = new Alumno(ALUMNO_GUIA.getNombre(),dni, ALUMNO_GUIA.getCorreo(), ALUMNO_GUIA.getTelefono(), ALUMNO_GUIA.getFechaNacimiento());
                //Alumno alumnoDni = new Alumno("Ficticio", dni, "correo@ficticio.com", "600000000", LocalDate.of(2000, 1, 1));
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validación del correo
        valido = false;
        do {
            System.out.print("Introduce el correo del alumno: ");
            try {
                correo = Entrada.cadena();
                if (correo == null || correo.isBlank()) {
                    throw new IllegalArgumentException("ERROR: El correo no puede ser nulo o vacío.");
                }
                Alumno alumnoCorreo = new Alumno(ALUMNO_GUIA.getNombre(),ALUMNO_GUIA.getDni(),correo, ALUMNO_GUIA.getTelefono(), ALUMNO_GUIA.getFechaNacimiento());
                //Alumno alumnoCorreo = new Alumno("Ficticio", dni ,correo, "600000000", LocalDate.of(2000, 1, 1));

                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validación del teléfono
        valido = false;
        do {
            System.out.print("Introduce el teléfono del alumno: ");
            try {
                telefono = Entrada.cadena();
                if (telefono == null || telefono.isBlank()) {
                    throw new IllegalArgumentException("ERROR: El telefono no puede ser nulo o vacío.");
                }
                Alumno alumnoDni = new Alumno(ALUMNO_GUIA.getNombre(),ALUMNO_GUIA.getDni(), ALUMNO_GUIA.getCorreo(), telefono, ALUMNO_GUIA.getFechaNacimiento());
                valido = true; // Si no lanza excepción, la entrada es válida
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validación de la fecha de nacimiento
        fechaNacimiento = leerFecha("Introduce la fecha de nacimiento del alumno");

        // Crear y retornar el alumno válido
        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
    }

   //6-Obtener alumno por Dni alumno
   public static Alumno getAlumnoPorDni() {
       String dni = null;
       boolean valido = false;
       do {
           System.out.print("Introduce el DNI del alumno: ");
           try {
               dni = Entrada.cadena();

               if (dni == null || dni.isBlank()) {
                   throw new IllegalArgumentException("ERROR: El DNI no puede ser nulo ni estar vacío.");
               }
               // Crear un alumno temporal para validar el DNI
               Alumno alumnoDni = new Alumno("Ficticio", dni, "correo@ficticio.com", "600000000", LocalDate.of(2000, 1, 1));
               valido = true; // Si no lanza excepción, el DNI es válido
           } catch (IllegalArgumentException e) {
               System.out.println(e.getMessage());
           }
       } while (!valido);

       // Retornar un Alumno ficticio con el DNI válido
       return new Alumno("Ficticio", dni, "correo@ficticio.com", "600000000", LocalDate.of(2000, 1, 1));
   }

    //Introduce fecha
    public static LocalDate leerFecha(String mensaje) {
        // Alumno ficticio para validar la fecha
        final Alumno ALUMNO_GUIA = new Alumno("Carlos", "12345678Z", "carlos.perez@gmail.com", "612345678",
                LocalDate.parse("15/03/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        LocalDate fechaNacimiento = null;
        boolean valida = false;
        do {
            System.out.print(mensaje + " (dd/MM/yyyy): ");
            String entradaFecha = Entrada.cadena();
            try {

                if (entradaFecha == null || entradaFecha.isBlank()) {
                    throw new IllegalArgumentException("ERROR: La fecha de nacimiento no puede ser nula ni estar vacía.");
                }

                fechaNacimiento = LocalDate.parse(entradaFecha, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                new Alumno(ALUMNO_GUIA.getNombre(), ALUMNO_GUIA.getDni(), ALUMNO_GUIA.getCorreo(), ALUMNO_GUIA.getTelefono(), fechaNacimiento);
                valida = true;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: El formato de la fecha no es válido. Use el formato dd/MM/yyyy.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valida);
        return fechaNacimiento;
    }

    public static Grado leerGrado() {
        System.out.println("Grados disponibles:");

        int index = 0;
        for (Grado grado : Grado.values()) {
            System.out.printf("%d. %s%n", index, grado);
            index++;
        }

        int opcion = -1;
        boolean valido = false;
        do {
            try {
                System.out.print("Elige un grado (introduce el número correspondiente): ");
                opcion = Entrada.entero();

                if (opcion < 0 || opcion >= Grado.values().length) {
                    throw new IllegalArgumentException("ERROR: Debes elegir un número dentro del rango de opciones.");
                }
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: Entrada no válida.");
            }
        } while (!valido);
        return Grado.values()[opcion];
    }

    public static CicloFormativo leerCicloFormativo() {
        int codigo = 0;
        String familiaProfesional = null;
        String nombre = null;
        int horas = 0;
        Grado grado = null;

        // Validación del código
        boolean valido = false;
        do {
            try {
                System.out.print("Introduce el código del ciclo formativo (4 dígitos): ");
                codigo = Entrada.entero();
                if (codigo < 1000 || codigo > 9999) {
                    throw new IllegalArgumentException("ERROR: El código debe ser un número de cuatro dígitos.");
                }
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: Entrada no válida. Por favor, introduce un número.");
            }
        } while (!valido);

        // Validación de la familia profesional
        valido = false;
        do {
            try {
                System.out.print("Introduce la familia profesional: ");
                familiaProfesional = Entrada.cadena();
                if (familiaProfesional == null || familiaProfesional.isBlank()) {
                    throw new IllegalArgumentException("ERROR: La familia profesional no puede ser nula o vacía.");
                }
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validación del nombre
        valido = false;
        do {
            try {
                System.out.print("Introduce el nombre del ciclo formativo: ");
                nombre = Entrada.cadena();
                if (nombre == null || nombre.isBlank()) {
                    throw new IllegalArgumentException("ERROR: El nombre no puede ser nulo o vacío.");
                }
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validación de las horas
        valido = false;
        do {
            try {
                System.out.print("Introduce el número de horas del ciclo formativo: ");
                horas = Entrada.entero();
                if (horas <= 0 || horas > 2000) {
                    throw new IllegalArgumentException("ERROR: El número de horas debe ser un valor positivo y menor o igual a 2000.");
                }
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: Entrada no válida. Por favor, introduce un número.");
            }
        } while (!valido);

        // Selección del grado usando leerGrado
        grado = leerGrado();

        // Creación y retorno del ciclo formativo
        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    public static void mostrarCiclosFormativos(ArrayList<CicloFormativo> ciclosFormativos) {
        if (ciclosFormativos == null || ciclosFormativos.isEmpty()) {
            System.out.println("No hay ciclos formativos disponibles para asociar a la asignatura.");
        }

        System.out.println("Selecciona un ciclo formativo:");
        for (int i = 0; i < ciclosFormativos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, ciclosFormativos.get(i));
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() {
        boolean valido = false;
        int codigo = 0;
        CicloFormativo validarCiclo = null;
        do {
            try {
                System.out.print("Introduce el código del ciclo formativo: ");
                codigo = Entrada.entero();
                validarCiclo =new CicloFormativo(codigo, "Ficticia", Grado.GDCFGM, "Ficticio", 1000);
                valido = true;
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                valido=false;
            }

        } while (!valido);
        return new CicloFormativo(codigo, "Ficticia", Grado.GDCFGM, "Ficticio", 1000);
    }

    public static Curso leerCurso() {
        System.out.println("Cursos disponibles:");
        int index = 0;
        for (Curso curso : Curso.values()) {
            System.out.printf("%d. %s%n", index, curso);
            index++;
        }
        int opcion = -1;
        boolean valido = false;
        do {
            try {
                System.out.print("Elige un curso (introduce el número correspondiente): ");
                opcion = Entrada.entero();

                if (opcion < 0 || opcion >= Curso.values().length) {
                    throw new IllegalArgumentException("ERROR: Debes elegir un número dentro del rango de opciones.");
                }
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: Entrada no válida. Por favor, introduce un número.");
            }
        } while (!valido);
        return Curso.values()[opcion];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("Especialidades disponibles:");
        int indice = 0;
        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad.imprimir());
            indice++;
        }
        EspecialidadProfesorado especialidadSeleccionada = null;
        boolean valido = false;
        do {
            System.out.print("Elige una especialidad (introduce el número correspondiente): ");
            try {
                int opcion = Entrada.entero();
                if (opcion < 0 || opcion >= EspecialidadProfesorado.values().length) {
                    throw new IllegalArgumentException("ERROR: La opción seleccionada no es válida.");
                }
                especialidadSeleccionada = EspecialidadProfesorado.values()[opcion];
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);
        return especialidadSeleccionada;
    }

    //ASIGNATURA
    public static Asignatura leerAsignatura(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        final CicloFormativo CICLO_FORMATIVO_GUIA = new CicloFormativo(1234, "Informática", Grado.GDCFGM, "Informática", 1000);

        final Asignatura ASIGNATURA_GUIA = new Asignatura("1234", "Matemáticas", 200, Curso.PRIMERO, 5,
                EspecialidadProfesorado.INFORMATICA, CICLO_FORMATIVO_GUIA);

        String codigo = null;
        String nombre = null;
        int horasAnuales = 0;
        Curso curso = null;
        int horasDesdoble = 0;
        EspecialidadProfesorado especialidad = null;
        CicloFormativo ciclo = null;

        // Validar código
        boolean valido = false;
        do {
            System.out.print("Introduce el código de la asignatura: ");
            try {
                codigo = Entrada.cadena();

                if (codigo == null || codigo.isBlank()) {
                    throw new IllegalArgumentException("ERROR: El código no puede ser nulo o vacío.");
                }
                new Asignatura(codigo, ASIGNATURA_GUIA.getNombre(), ASIGNATURA_GUIA.getHorasAnuales(),
                        ASIGNATURA_GUIA.getCurso(), ASIGNATURA_GUIA.getHorasDesdoble(),
                        ASIGNATURA_GUIA.getEspecialidadProfesorado(), ASIGNATURA_GUIA.getCicloFormativo());
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validar nombre
        valido = false;
        do {
            System.out.print("Introduce el nombre de la asignatura: ");
            try {
                nombre = Entrada.cadena();
                if (nombre == null || nombre.isBlank()) {
                    throw new IllegalArgumentException("ERROR: El nombre no puede ser nulo o vacío.");
                }
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validar horas anuales
        valido = false;
        do {
            System.out.print("Introduce las horas anuales de la asignatura: ");
            try {
                horasAnuales = Entrada.entero();
                // Crear una asignatura temporal para validar las horas
                new Asignatura(ASIGNATURA_GUIA.getCodigo(), ASIGNATURA_GUIA.getNombre(),
                        horasAnuales, ASIGNATURA_GUIA.getCurso(), ASIGNATURA_GUIA.getHorasDesdoble(),
                        ASIGNATURA_GUIA.getEspecialidadProfesorado(), ASIGNATURA_GUIA.getCicloFormativo());
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);


        // Validar curso
        curso = leerCurso();

        // Validar horas de desdoble
        valido = false;
        do {
            System.out.print("Introduce las horas de desdoble de la asignatura: ");
            try {
                horasDesdoble = Entrada.entero();

                new Asignatura(ASIGNATURA_GUIA.getCodigo(), ASIGNATURA_GUIA.getNombre(),
                        ASIGNATURA_GUIA.getHorasAnuales(), ASIGNATURA_GUIA.getCurso(), horasDesdoble,
                        ASIGNATURA_GUIA.getEspecialidadProfesorado(), ASIGNATURA_GUIA.getCicloFormativo());
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validar especialidad del profesorado
        especialidad = leerEspecialidadProfesorado();

        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidad, cicloFormativo);
    }

    public static Asignatura getAsignaturaPorCodigo() {
        String codigo = null;
        boolean valido = false;
        do {
            System.out.print("Introduce el código de la asignatura (4 dígitos): ");
            try {
                codigo = Entrada.cadena();
                if (codigo == null || codigo.isBlank() || !codigo.matches("\\d{4}")) {
                    throw new IllegalArgumentException("ERROR: El código debe ser un número de 4 dígitos.");
                }
                valido = true; // Si no lanza excepción, el código es válido
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);
        return new Asignatura(codigo, "Ficticia", 100, Curso.PRIMERO, 5, EspecialidadProfesorado.INFORMATICA, new CicloFormativo(1234, "Ficticio", Grado.GDCFGM, "Grado Medio", 1000));
    }

    private static void mostrarAsignaturas(ArrayList<Asignatura> asignaturas) {
        if (asignaturas == null || asignaturas.size() == 0) {
            System.out.println("No hay asignaturas registradas en el sistema.");
            return;
        }
        System.out.println("Listado de asignaturas registradas:");
        for (int i = 0; i < asignaturas.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, asignaturas.get(i));
        }
    }

    static boolean asignaturaYaMatriculada(ArrayList<Asignatura> asignaturasMatricula, Asignatura asignatura) {
        for (Asignatura asign : asignaturasMatricula) {
            if (asign != null && asign.equals(asignatura)) {
                return true;
            }
        }
        return false;
    }

    public static Matricula leerMatricula(Alumno alumno, ArrayList<Asignatura> asignaturas) throws OperationNotSupportedException {
        final Matricula MATRICULA_GUIA = new Matricula(1, "23-24", LocalDate.now(),
                new Alumno("Carlos", "12345678Z", "carlos.perez@gmail.com", "612345678", LocalDate.of(2000, 1, 1)),
                new ArrayList<>());

        if (alumno == null) {
            System.out.println("ERROR: El alumno no puede ser nulo.");
            return null;
        }

        if (asignaturas == null || asignaturas.size() == 0) {
            System.out.println("ERROR: Debes proporcionar al menos una asignatura.");
            return null;
        }

        int idMatricula = 0;
        LocalDate fechaMatriculacion = null;
        LocalDate fechaAnulacion = null;

        // Validar el ID de matrícula
        boolean valido = false;
        do {
            System.out.print("Introduce el identificador de la matrícula: ");
            try {
                idMatricula = Entrada.entero();
                if (idMatricula <= 0) {
                    throw new IllegalArgumentException("ERROR: El identificador de la matrícula debe ser un número positivo.");
                }
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validar la fecha de matriculación
        valido = false;
        do {
            System.out.print("Introduce la fecha de matriculación (dd/MM/yyyy): ");
            try {
                String entrada = Entrada.cadena();
                fechaMatriculacion = LocalDate.parse(entrada, DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA));

                Matricula matriculaTemporal = new Matricula(MATRICULA_GUIA.getIdMatricula(), MATRICULA_GUIA.getCursoAcademico(),
                        fechaMatriculacion, MATRICULA_GUIA.getAlumno(), MATRICULA_GUIA.getColeccionAsignaturas());
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: La fecha no tiene un formato válido. Use el formato dd/MM/yyyy.");
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Validar la fecha de anulación (opcional)
        valido = false;
        do {
            System.out.print("Introduce la fecha de anulación (dd/MM/yyyy) o presione Enter para omitir: ");
            String fechaAnulacionInput = Entrada.cadena();

            if (fechaAnulacionInput.isBlank()) {
                // Si el usuario presiona Enter, salimos del bucle sin validar
                valido = true;
                fechaAnulacion = null;
                break;
            }

            try {
                fechaAnulacion = LocalDate.parse(fechaAnulacionInput, DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA));

                Matricula matriculaTemporal = new Matricula(MATRICULA_GUIA.getIdMatricula(), MATRICULA_GUIA.getCursoAcademico(),
                        fechaMatriculacion, MATRICULA_GUIA.getAlumno(), MATRICULA_GUIA.getColeccionAsignaturas());
                matriculaTemporal.setFechaAnulacion(fechaAnulacion);

                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: La fecha no tiene un formato válido. Use el formato dd/MM/yyyy.");
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (!valido);


        // Calcular el curso académico basado en la fecha de matriculación
        String cursoAcademico;
        int anioInicio = fechaMatriculacion.getYear();
        int anioFin = anioInicio + 1;
        cursoAcademico = String.format("%02d-%02d", anioInicio % 100, anioFin % 100);


        // Validar y seleccionar asignaturas
        System.out.println("Selecciona las asignaturas para la matrícula:");
        ArrayList<Asignatura> asignaturasSeleccionadas = elegirAsignaturasMatricula(asignaturas);

        // Verificar si no se seleccionaron asignaturas
        if (asignaturasSeleccionadas == null || asignaturasSeleccionadas.size() == 0) {
            System.out.println("ERROR: No se seleccionó ninguna asignatura para la matrícula.");
            return null;
        }

        // Crear y devolver la matrícula válida
        Matricula matricula = new Matricula(idMatricula,cursoAcademico, fechaMatriculacion, alumno, asignaturas);
        matricula.setFechaAnulacion(fechaAnulacion); // Si no se introdujo fecha de anulación, será null
        return matricula;
    }

    public static ArrayList<Asignatura> elegirAsignaturasMatricula(ArrayList<Asignatura> asignaturas) {
        if (asignaturas == null || asignaturas.size() == 0) {
            System.out.println("No hay asignaturas disponibles para elegir.");
        }

        Consola.mostrarAsignaturas(asignaturas);

        ArrayList<Asignatura> seleccionadas = new ArrayList<>();
        int indiceSeleccionadas = 0;

        while (indiceSeleccionadas < Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            System.out.print("Introduce el número de la asignatura para añadirla a la matrícula (0 para finalizar): ");
            try {
                int opcion = Entrada.entero();

                if (opcion == 0) {
                    if (seleccionadas.isEmpty()) {
                        System.out.println("ERROR: No se seleccionó ninguna asignatura para la matrícula.");
                        continue;
                    }
                    break;
                }

                if (opcion < 1 || opcion > asignaturas.size()) {
                    System.out.println("ERROR: Selección no válida. Introduce un número entre 1 y " + asignaturas.size() + ".");
                    continue;
                }

                Asignatura asignaturaSeleccionada = asignaturas.get(opcion - 1);

                if (asignaturaYaMatriculada(seleccionadas, asignaturaSeleccionada)) {
                    System.out.println("ERROR: La asignatura ya ha sido seleccionada.");
                    continue;
                }

                seleccionadas.add(asignaturaSeleccionada);
                indiceSeleccionadas++;
                System.out.println("Asignatura añadida: " + asignaturaSeleccionada);

            } catch (Exception e) {
                System.out.println("ERROR: Entrada no válida. Debes introducir un número.");
            }
        }

        if (indiceSeleccionadas == 0) {
            System.out.println("No se seleccionó ninguna asignatura.");
            return new ArrayList<>();
        }

        return seleccionadas;
    }

    public static Matricula getMatriculaPorIdentificador() {
        int idMatricula = 0;
        boolean valido = false;

        do {
            System.out.print("Introduce el identificador de la matrícula: ");
            try {
                idMatricula = Entrada.entero(); // Leer el ID de la matrícula
                if (idMatricula <= 0) {
                    throw new IllegalArgumentException("ERROR: El identificador debe ser un número positivo.");
                }
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        // Crear una matrícula ficticia con datos válidos para devolver en caso de éxito
        try {
            Alumno alumnoFicticio = new Alumno("Ficticio", "12345678Z", "ficticio@correo.com", "600000000", LocalDate.of(2000, 1, 1));

            ArrayList<Asignatura> asignaturasFicticias = new ArrayList<>();
            asignaturasFicticias.add(new Asignatura("1234", "Ficticia", 200, Curso.PRIMERO, 5, EspecialidadProfesorado.INFORMATICA,
                    new CicloFormativo(1234, "Ficticio", Grado.GDCFGM, "Grado Medio", 1000)));

            return new Matricula(idMatricula, "23-24", LocalDate.now(), alumnoFicticio, asignaturasFicticias);
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo crear una matrícula ficticia válida.");
            return null;
        }
    }
}
