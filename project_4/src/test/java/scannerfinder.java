import org.apache.log4j.BasicConfigurator;
import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import org.pcap4j.core.Pcaps;
public class scannerfinder {
    public static void main(String args[]) throws PcapNativeException, NotOpenException {
    // Code sourced from https://www.devdungeon.com/content/packet-capturing-java-pcap4j
        BasicConfigurator.configure();
        PcapHandle readFile;
        try{
            readFile = Pcaps.openOffline("/home/alex/Documents/Montana_State/2019/Spring/CSCI_476/CSCI_476/Assignment4_maven/lbl-internal.20041004-1305.port002.dump.pcap", PcapHandle.TimestampPrecision.NANO);

        } catch (PcapNativeException e) {
        readFile = Pcaps.openOffline("lbl-internal.20041004-1305.port002.dump.pcap");
        }
        Packet received = readFile.getNextPacket();
    System.out.println("Packets received: " + received);
    }
}