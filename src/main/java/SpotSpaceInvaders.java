import detector.DetectorService;
import detector.strategy.BruteForcePatternDetector;
import pattern.PatternFactory;
import reader.DefaultFileReaderWrapper;
import reader.RadarReaderService;
import result.ResultService;

public class SpotSpaceInvaders {

    public static void main(String[] args) {
        PatternFactory patternFactory = new PatternFactory();
        DefaultFileReaderWrapper defaultFileReaderWrapper = new DefaultFileReaderWrapper();
        RadarReaderService radarReaderService = new RadarReaderService(patternFactory, defaultFileReaderWrapper);
        BruteForcePatternDetector bruteForcePatternDetector = new BruteForcePatternDetector();
        DetectorService detectorService = new DetectorService(bruteForcePatternDetector);
        ResultService resultService = new ResultService(detectorService, radarReaderService);
        resultService.result();
    }
}