(ns handler)

(defn merge [dataset-1 dataset-2]
  (flatten (conj dataset-1 dataset-2)))

(defn save [data]
  (str "SAVED:" data))

(defn handle-data [dataset-1 dataset-2]
  (let [merged-data (merge dataset-1 dataset-2)]
    (vec (map #(save %) merged-data))))