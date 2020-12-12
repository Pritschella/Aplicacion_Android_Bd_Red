<?php

    include('../sitio/scripts_php/conexion_bd.php');

    $con = new ConexionBD();
    $conexion = $con->getConexion();

    //var_dump($conexion);

    if($_SERVER['REQUEST_METHOD'] =='POST'){
        $cadena_JSON = file_get_contents('php://input'); //Preparar PHP para recibir informacion a traves de HTTP
        if($cadena_JSON == false){
            echo "No hay cadena JSON";
        }else{
            $datos = json_decode($cadena_JSON, true);

            $dato = $datos['n'];                

            
            $sql = "SELECT * FROM alumnos where Num_Control like '%$dato%' OR Nombre_Alumno like '%$dato%' OR Primer_Ap_Alumno like '%$dato%'";

                //var_dump($res);
                $res = mysqli_query($conexion, $sql);

            
                $datos['alumnos'] = array();
                if($res){
                    //todo bien

                    while($fila = mysqli_fetch_assoc($res)){
                        $alumno = array();

                        $alumno['nc'] = $fila['Num_Control'];
                        $alumno['n'] = $fila['Nombre_Alumno'];
                        $alumno['pa'] = $fila['Primer_Ap_Alumno'];
                        $alumno['sa'] = $fila['Segundo_Ap_Alumno'];
                        $alumno['e'] = $fila['Edad'];
                        $alumno['s'] = $fila['Semestre'];
                        $alumno['c'] = $fila['Carrera'];

                        array_push($datos['alumnos'], $alumno);
                    }

                    echo json_encode($datos);

                }else{
                    //todo mal
                    $respuesta['exito'] = false;
                    $respuesta['mensaje'] = "ERROR en la insercion";
                    $cad = json_encode($respuesta);
                    //var_dump($cad);
                    echo $cad;
                }
        }

       

    }else
        echo "No hay peticion HTTP";
    

?>