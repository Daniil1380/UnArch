package main.java.com.daniil1380.UnArch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Archives {
    private List<Archive> archives;

    public List<Archive> getArchives() {
        return archives;
    }

    public void setArchives(List<Archive> archives) {
        this.archives = archives;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Archive {
        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }

}
