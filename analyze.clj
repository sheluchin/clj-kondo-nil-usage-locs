(ns analyze
  (:require [clj-kondo.core :as clj-kondo]
            [clojure.pprint :as pprint]))

(defn analyze-path
  "Analyze a path with clj-kondo and return the var-usages map"
  [path]
  (let [analysis (clj-kondo/run! {:lint [path]
                                  :config {:output {:analysis {:var-usages true}}}})]
    (get-in analysis [:analysis :var-usages])))

(defn -main
  "Entry point for the script. Analyzes the provided path and prints var-usages."
  [& args]
  (let [path (first args)
        var-usages (analyze-path path)
        filtered-var-usages (filterv #(nil? (:name-col %)) var-usages)
        labeled-usages (mapv #(assoc % :nil-name-col? (nil? (:name-col %)))
                             var-usages)]
    (println "Var Usages:")
    (spit "filtered.edn" (with-out-str (pprint/pprint filtered-var-usages)))
    (spit "labeled.edn" (with-out-str (pprint/pprint labeled-usages)))
    (pprint/pprint labeled-usages)))
