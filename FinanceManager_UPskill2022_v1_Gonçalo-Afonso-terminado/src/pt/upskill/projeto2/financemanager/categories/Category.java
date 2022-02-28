package pt.upskill.projeto2.financemanager.categories;

import org.junit.experimental.categories.Categories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Category implements Serializable {

    private String name;
    //create an array list with all categories (will fill up after the serialization of the file);
    private static List<Category> categories = null;
    //create an array list with all the tags received from the csv file (ex: summary , purchase , etc)
    private List<String> tags = new ArrayList<>();
    //don't know what is doing
    private static final long serialVersionUID = -9107819223195202547L;

    public Category(String string) {
        this.name = string;

    }

    /**
     * Função que lê o ficheiro categories e gera uma lista de {@link Category} (método fábrica)
     * Deve ser utilizada a desserialização de objetos para ler o ficheiro binário categories.
     *
     * @param file - Ficheiro onde estão apontadas as categorias possíveis iniciais, numa lista serializada (por defeito: /account_info/categories)
     * @return uma lista de categorias, geradas ao ler o ficheiro
     */
    //method to de-serialize the categories doc
    public static List<Category> readCategories(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
    //will put the info that was inside the  serializa categories doc on the array list categories
            categories = (List) in.readObject();

            in.close();
            fileIn.close();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    //will read the content in array list categories
        categories.forEach((n)-> System.out.println(n));
        return categories;

    }

    /**
     * Função que grava no ficheiro categories (por defeito: /account_info/categories) a lista de {@link Category} passada como segundo argumento
     * Deve ser utilizada a serialização dos objetos para gravar o ficheiro binário categories.
     * @param file
     * @param categories
     */
    public static void writeCategories(File file, List<Category> categories) {
        //is only used for writing serialized files (category)
        // TODO completar o código da função
    }

    public boolean hasTag(String tag) {
    //if arraylist tags has a size bigger than 0 (not empty) ,
        if (tags.size() > 0) {
            for (String s : tags) {
                if (s.equals(tag)) {
                    return true;
                }
            }
        }return false;
    }

    public void addTag(String tag) {
        if(!hasTag(tag));
        tags.add(tag);
    }

    public String getName() {
        return name;
    }


    public List<String> getTags() {
        return tags;
    }
    //necessary to convert the info in the array list to readable
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", tags=" + tags +
                '}';
    }
}
