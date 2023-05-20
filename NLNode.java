//Importing important packages
import java.util.Comparator; //Importing Comparator package
import java.util.Iterator; //Importing Iterator package

public class NLNode<T>
{
    //Instance variables
    private NLNode<T> parent; //reference to parent
    private ListNodes<NLNode<T>> children; //reference to a list storing the children
    private T data; //reference to the data object

    //Default constructor
    public NLNode(FileObject childFileObject)
    {
        this.parent = null;
        this.data = null;
        this.children = new ListNodes<NLNode<T>>();
    }

    //Constructor
    public NLNode(T d, NLNode<T> p)
    {
        this.children = new ListNodes<NLNode<T>>();
        this.data = d;
        this.parent = p;
    }

    //Function to set parent node
    public void setParent(NLNode<T> p)
    {
        this.parent = p;
    }

    //Function to get parent node
    public NLNode<T> getParent()
    {
        return this.parent;
    }

    //Function to add new child
    public void addChild(NLNode<T> newChild)
    {
        this.children.add(newChild);
        newChild.setParent(this); //Setting the parent node of this child
    }

    //Function to return iterator containing children
    public Iterator<NLNode<T>> getChildren() {
        return children.getList();
    }

    //Function to return iterator containing children sorted in the order specified by the parameter sorter
    public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter)
    {
        return this.children.sortedList(sorter);
    }

    //Function to return data stored in this node
    public T getData()
    {
        return this.data;
    }

    //Function set data in this node
    public void setData(T d)
    {
        this.data = d;
    }
}
