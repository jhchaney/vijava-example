/**
 * Created by Chaney on 3/20/17.
 */

import com.vmware.vim25.*;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

import java.net.MalformedURLException;
import java.net.URL;

public class Example {

    public static void main (String args[]) throws Exception{
        String urlStr = "https://esx-server/sdk";
        String username = "username";
        String password = "password";
        String vmName = "TestVM";
        ServiceInstance si = new ServiceInstance(new URL(urlStr), username, password,true);
        Folder rootFolder = si.getRootFolder();
        VirtualMachine []vm = (VirtualMachine []) new
                InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
        for (int i = 0; i < vm.length; i++) {
            if(vm[i].getName().equals(vmName)){
                vm[i].powerOffVM_Task();
                Thread.sleep(60000);
                VirtualMachinePowerState powerState = vm[i].getSummary().getRuntime().getPowerState();
                if(powerState==VirtualMachinePowerState.poweredOff){
                    //test passed.
                }
                else{
                    //test failed.
                }
            }
        }
    }
}
