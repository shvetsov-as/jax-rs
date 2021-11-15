/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rws;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author User
 */
public class FileSelector implements FilenameFilter{
    
    private String fragment;
    private boolean regexp;

    public FileSelector(String fragment) {
        this.fragment = fragment;
        regexp = false;
        System.out.println("constructor : regexp false = " + regexp);
    }

    public FileSelector(String fragment, boolean regexp) {
        this.fragment = fragment;
        this.regexp = regexp;
        System.out.println("constructor : regexp = " + regexp);
    }
    
    

    @Override
    public boolean accept(File dir, String fileName) { //fileName - imya sledyushego faila; T - esli nujno vkluchitb file v spisok; F - ne nyjno vkluchatb
        
        if (regexp){
            System.out.println("fragment " + fragment + " fileName " + fileName);
            return fileName.matches(fragment);//T - file fkluchen v rezultiryushiy spisok, F - ne vkluchen
        }else{
            System.out.println("fragment2 " + fragment + " fileName2 " + fileName);
            return fileName.contains(fragment);//fragment soderjitsia v imyani
        }
        
        
    }
    
}
