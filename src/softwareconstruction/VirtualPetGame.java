package softwareconstruction;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author kealenpillay
 */
public class VirtualPetGame 
{
    public VirtualPetGame()
    {
        Model model = new Model();
        View view = new View("Virtual Pet Game", model);
        model.addObserver(view);
        Controller controller = new Controller(model, view);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = view.getSize();
        view.setLocation((screenDimension.width-frameDimension.width)/2, (screenDimension.height-frameDimension.height)/2);
        view.setVisible(true);
        model.getDbManager().closeConnections();
    }
    
    
    public static void main(String[] args) 
    {
        VirtualPetGame game = new VirtualPetGame(); 
    }  
}
