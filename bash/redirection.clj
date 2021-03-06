(ns com.burleyarch.bash)

;; (require '[com.burleyarch.bash :as bash])

(defmacro <
  "Evaluate body with *in* bound to file"
  [p & body]
  `(with-open [my-in# (clojure.java.io/reader ~p)]
     (binding [*in* my-in#] (doall ~@body))))

(defmacro >
  "Evaluate body with *out* bound to file"
  [p & body]
  `(with-open [my-out# (clojure.java.io/writer ~p)]
     (binding [*out* my-out#] (doall ~@body))))

(defmacro >>
  "Evaluate body with *out* bound to file, appending to the end"
  [p & body]
  `(with-open [my-out# (clojure.java.io/writer ~p :append true)]
     (binding [*out* my-out#] (doall ~@body))))
