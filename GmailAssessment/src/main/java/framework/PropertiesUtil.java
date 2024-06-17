package framework;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class PropertiesUtil {

    private InputStreamReader isr;
    private PropertiesConfiguration propertiesConfiguration;
    private PropertiesConfigurationLayout propertiesConfigurationLayout;

    public PropertiesUtil()
    {
        try
        {
            isr = new InputStreamReader(new FileInputStream("ConfigFile.properties"));
            propertiesConfiguration = new PropertiesConfiguration();
            propertiesConfigurationLayout = new PropertiesConfigurationLayout();
            propertiesConfigurationLayout.load(propertiesConfiguration,isr);
        }

        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /****************************************************************************************************************/

    public String getPropertyValue(String key)
    {
        return propertiesConfiguration.getString(key).replace("[","").replace("]","").trim();
    }

    public String getEmailID()
    {
        return getPropertyValue("EmailID");
    }

    public String getPassword()
    {
        return getPropertyValue("Password");
    }
}
