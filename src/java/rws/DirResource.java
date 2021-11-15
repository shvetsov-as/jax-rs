/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rws;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("dir")
public class DirResource implements IDirectory {

    @Context
    private UriInfo context;

    public static final String HOME_DIR = "C:/Users/User/Desktop/home";

    /**
     * Creates a new instance of DirResource
     */
    public DirResource() {
    }

    @Override
    @GET
    @Path("{directory}")
    @Produces("text/html")
    public String getContent(@PathParam("directory") String dirName) {

        System.out.println("called method getContent dir = " + dirName);
        return getFolderContent(dirName);
    }

    
    @Override
    @GET
    @Path("find/{directory}")
    @Produces("text/html")
    public String findFile(@PathParam("directory")String dirName, @QueryParam("file")String fileName, @QueryParam("flag")boolean flag) {
        System.out.println("called method findFile dir = " + dirName + " file = " + fileName + "flag = " + flag);
        
        return findFileByFragment(dirName, fileName, flag);
    }


//    //@Override
//    @GET
//    @Path("regexp/{directory}")
//    @Produces("text/html")
//    public String findFileRegexp(@PathParam("directory") String dirName, @QueryParam("file") String fileName, @QueryParam("regexp") String regexp) {
//        System.out.println(" DirResource findFile(). dirName = " + dirName + " file = " + fileName);
//        return findFileByFragment(dirName, fileName, regexp);
//    }
    
    //http://localhost:8080/J210REST_lab3_test_1/webresources/dir/hello dlia proverki
    @GET
    @Path("hello")
    @Produces("text/plain")
    public String hello() {

        return "date: " + (new Date()).toString();

    }

    private String getFolderContent(String dirName) {

        StringBuilder sb = new StringBuilder();
        File folder = null;

        if (dirName.equals("HOME")) {
            folder = new File(HOME_DIR);
        } else {
            folder = new File(HOME_DIR + File.separator + dirName);
        }
        System.out.println("folder = " + folder.getAbsolutePath());
//        if (!folder.exists()){
//            sb.append("Folder " + dirName + " doesnt exists");
//        }else{}

        //folder.listFiles(); vernet null esli papka ne sushestvuet
        File[] files = folder.listFiles();
        //papki net
        if (files == null) {
            return "<h2> Folder " + dirName + " doesnt exist </h2>";
        }
        //papka pysta
        if (files.length == 0) {
            return "<h2> Folder " + dirName + " is empty </h2>";
        }
        // obhodim papky
        sb.append("<h2>Directory " + dirName + " contains: </h2> <ol><big>");
        for (File f : files) {
            sb.append("<li>" + f.getName() + " - " + (f.isDirectory() ? "folder" : "file"));
        }
        sb.append("</big></ol>");

        return sb.toString();
    }

    private String findFileByFragment(String dirName, String fragment, boolean flag) {//, String regexp
        StringBuilder sb = new StringBuilder();
        File folder = null;
        //oprediliaem katalog
        if (dirName.equals("HOME")) {
            folder = new File(HOME_DIR);
        } else {
            folder = new File(HOME_DIR + File.separator + dirName);
        }
        //poluchaem spisok
        System.out.println("ListFiles flag = " + flag);
        File[] files = folder.listFiles(new FileSelector(fragment, flag));//FileSelector(fragment, true - budet dobavliatsia pri vbIzove)

        if (files == null) {
            return "<h2> Folder " + dirName + " doesnt exist </h2>";
        }

        if (files.length == 0) {
            return "<h2> File not found </h2>";
        }

        sb.append("<h2>In directory " + dirName + " we found: </h2> <ol><big>");
        for (File f : files) {
            sb.append("<li>" + f.getName() + " - " + (f.isDirectory() ? "folder" : "file"));
        }
        sb.append("</big></ol>");

        return sb.toString();
    }

    /**
     * Retrieves representation of an instance of rws.DirResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ReturnData getXml() {
        return new ReturnData();
    }
//
//    /**
//     * PUT method for updating or creating an instance of DirResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }

    
}
