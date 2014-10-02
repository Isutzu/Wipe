import java.io.*;
import java.util.*;

public class WipeFile
{

/*************************die()**********************************/
public static void die(String msg)
{
   System.err.println(msg);
   
}

/************************rand()**********************************/
public static int rand(int a, int b)
{
   return((int)((b-a+1)* Math.random())+a);
}

/************************fileValidation()**********************************/
public static boolean fileValidation(File f)
{
   if (!f.exists())
   {
      die( f.getName() + ": File does not exists");
      return false;
   }
   else if (!f.canRead())
   {
      die( f.getName() + ": can not read from File");
      return false;
   }
   else if (!f.isFile())
   {
      die( f.getName() + ": Not a File");
      return false;
   }


   else
      return true;
}


/************************delete()**********************************/
public static void delete(File f) throws IOException
{
    // The length of a file is returned in bytes
    // we need convert it to integer
    // we convert it to string first 
    // then we convert it to int with Integer.parseInt()
     String l = "" + f.length();
     PrintWriter pw = new PrintWriter(f);
     System.out.println("deleting file... " + f.getName());

   for (int i = 0; i < Integer.parseInt(l)-1; i++)
   {
      pw.print((char)(rand(65,125))); //random ascii chars between 65(A) to 125(})

   }
     pw.print("\n"); 
     pw.close();

   f.delete();
}


/************************question()**********************************/
public static void question(File f, boolean ask) throws IOException
{
   if (ask)
   {
      System.out.print("Remove file: " + f.getName() + "? ");
      Scanner sc = new Scanner(System.in);
      String ans = sc.nextLine();
      if(Character.toUpperCase(ans.charAt(0)) == 'Y')   
      {
         delete(f);
      }
       
   }
   else
      delete(f);
}

/************************main()**********************************/
public static void main (String[] args) throws IOException
{
   if (args.length == 0)
      die("usage: javac WipeFile [-c] file1 [file2]..[filen]");
   
   if (args.length == 1)
   {
      String fname = args[0];
      if(args[0].equals("-c"))
         die("usage: javac WipeFile [-c] file1 [file2]..[filen]");
      else
      {
         File f = new File(fname);

         if(fileValidation(f))
            question(f,false);
      }
   }

   if(args.length > 1)
   {
    
     boolean flag = true;
     boolean ask = false;
      for(String fname:args)
      {
         if(fname.equals("-c") && flag)
         {
           ask  = true; 
           flag = false;   
         }
         else
         {
            flag = false;
            File f = new File(fname);
            if ( fileValidation(f) )
               question(f,ask);
         }

      }
   }
       
}

}// end of class
