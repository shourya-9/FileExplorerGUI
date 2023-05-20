//Importing important packages
import java.util.ArrayList; //Importing ArrayList package
import java.util.Iterator; //Importing Iterator package

public class FileStructure
{
    //Instance variable
    private NLNode<FileObject> root; //reference to root node

    //Class constructor
    public FileStructure(String fileObjectName) throws FileObjectException {
        FileObject f = new FileObject(fileObjectName);
        if(f.isDirectory()) //Checking if the object is a folder
        {
            root = new NLNode<>(f, null);
            recStructure(root); //Calling the recursive helper method
        }
        else root = new NLNode<>(f, null);
    }

    //Recursive helper method to create nodes of the file structure tree
    private void recStructure(NLNode<FileObject> r)
    {
        if (r.getData().isDirectory())
        {
            Iterator<FileObject> iter = r.getData().directoryFiles(); //Getting an iterator
            while (iter.hasNext()) //While loop to go through the objects and creating new nodes for the structure
            {
                FileObject childObject = iter.next();
                NLNode<FileObject> child = new NLNode<FileObject>(childObject, r);
                r.addChild(child); //Adding child node
                recStructure(child); //Recursive call
            }
        }
    }

    //Method to return root node
    public NLNode<FileObject> getRoot()
    {
        return root;
    }

    //Method to return String iterator with the names of all the files of the specified type
    public Iterator<String> filesOfType(String type)
    {
        ArrayList<String> fileNames = new ArrayList<String>();
        recFilesOfType(root, type, fileNames);
        Iterator<String> List = fileNames.iterator();
        return List;
    }

    //Recursive helper method to go through the nodes and store the names of similar files
    private void recFilesOfType(NLNode<FileObject> r, String type, ArrayList<String> fileNames)
    {
        if (r.getData().isFile() && r.getData().getLongName().endsWith(type)) //Checking if the files end with same file type
            fileNames.add(r.getData().getLongName()); //Adding node data if similar
        else if (r.getData().isDirectory()) //Checking if the node contains a folder or directory
        {
            Iterator<NLNode<FileObject>> iter = r.getChildren(); //Getting an iterator to store the objects
            while (iter.hasNext()) //
            {
                NLNode<FileObject> n = iter.next();
                recFilesOfType(n, type, fileNames);
            }
        }
    }

    //Function to look for a file with specified name inside the file structure
    public String findFile(String name) {
        return recFindFile(this.root, name);
    }

    //Recursive helper method to find the file for the findFile method
    private String recFindFile(NLNode<FileObject> node, String name)
    {
        FileObject f = node.getData();
        if (f.isFile() && f.getName().equals(name)) //Checking if the file has the same name
            return f.getLongName();
        else if (f.isDirectory()) //Checking if the node is a folder or directory
        {
            Iterator<NLNode<FileObject>> iter = node.getChildren(); //Getting an iterator
            while (iter.hasNext()) {
                String result = recFindFile(iter.next(), name);
                if (!result.isEmpty()) //Checking if the String variable is empty or not
                    return result;
            }
        }
        return ""; //Returning an empty string if not found
    }
}