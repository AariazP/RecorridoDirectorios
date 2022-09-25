package co.uniquindio.edu.model;

import lombok.Data;

@Data
public class MyFile {


    private String name;

    public MyFile (String name){
        this.name = name;
    }


}
