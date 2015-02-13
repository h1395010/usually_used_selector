import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Filter 
{
  Set<Character> keys = new HashSet<>();
  PrintWriter osw; 
  void checkAndDump( List<String> lines ) throws Exception 
  {
    if( lines.size() >= 1 &&
        keys.contains( lines.get(0).charAt(0) ) )
    {
      for( String s: lines )
      {
        osw.println( s );
      }
      osw.println();
    }
    lines.clear();
  }

  void filter( String inpath, String outpath ) throws Exception 
  {
    BufferedReader lr = new BufferedReader( new FileReader( inpath ) );
    osw = new PrintWriter( new FileOutputStream( outpath ) );
    String line;
    List<String> lines = new ArrayList<>();
    while( (line = lr.readLine()) != null )
    {
      if( line.length() > 0 )
      {
        lines.add( line );
      } 
      else 
      {
        checkAndDump( lines );
      }
    }
    checkAndDump( lines );
    osw.close();
    lr.close();
  }

  void fillSet( String path ) throws Exception 
  {
    BufferedReader br = new BufferedReader( new FileReader( path ) );
    String line;
    while( (line = br.readLine()) != null )
    {
      if( line.length() > 0 )
      {
        keys.add( line.charAt(0) );
      }
    }
    br.close();
  }    

  public static void main( String[] args ) throws Exception 
  {
    Filter f = new Filter();
    f.fillSet( "/home/matthias/Workbench/SUTD/1_February/usually_used_selector/usually_used_selector/spiders/main_used_characters_reference.txt" );
    f.filter( "/home/matthias/Dropbox/Work/Code/character_component_scraper/character_component_document.txt" , "/home/matthias/Workbench/SUTD/1_February/usually_used_selector/usually_used_selector/spiders/stack_overflow_output.txt");
  }
}