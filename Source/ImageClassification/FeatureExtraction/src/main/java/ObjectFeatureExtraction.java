import org.openimaj.feature.local.list.LocalFeatureList;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.feature.local.engine.DoGSIFTEngine;
import org.openimaj.image.feature.local.keypoints.Keypoint;

import java.io.*;

/**
 * Created by Naga on 20-09-2016.
 */
public class ObjectFeatureExtraction {
    public static void main(String args[]) throws IOException {
        String inputFolder = "data/";
        String inputImage = "Breast2.png";
        String outputFolder = "output/";
        String[] IMAGE_CATEGORIES = {"Brain", "Lung", "Breast", "Prostate"};
        int input_class = 3;
        MBFImage mbfImage = ImageUtilities.readMBF(new File(inputFolder + inputImage));
        DoGSIFTEngine doGSIFTEngine = new DoGSIFTEngine();
        LocalFeatureList<Keypoint> features = doGSIFTEngine.findFeatures(mbfImage.flatten());
        FileWriter fw = new FileWriter(outputFolder + IMAGE_CATEGORIES[input_class] + ".txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < features.size(); i++) {
            double c[] = features.get(i).getFeatureVector().asDoubleVector();
            bw.write(input_class + ",");
            for (int j = 0; j < c.length; j++) {
                bw.write(c[j] + " ");
            }
            bw.newLine();
        }
        bw.close();
    }
}
