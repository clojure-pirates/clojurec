(ns clojurec.core-test
  (:use clojure.test
        clojurec.core))

(deftest basic
  (testing "very basic stuff"
    (is (= (run-expr '(print (has-type? 1 Integer))) [true]))
    (is (= (run-expr '(def heusler (fn* ([x] x)))) []))
    (is (= (run-expr '(print (let [a 1 a a] a))) [1]))
    (is (= (run-expr '(print (((fn* ([x] (fn* ([y] x)))) 1) 2))) [1]))
    (is (= (run-expr '(do (print 1) (print 2))) [1 2]))))

(deftest protocols
  (testing "protocols"
    (is (= (run-expr '(do
			(defprotocol* cljc.core/IFn
			  (-invoke [f & args]))
			(let [x (fn [] 1)]
			  (print (. x -invoke)))))
	   [1]))))

(deftest types
  (testing "deftype"
    (is (= (run-expr '(deftype* Cons [first rest])) []))
    (is (= (run-expr '(do
			(deftype* Cons [first rest])
			(def f (fn [c] (c* "DEFTYPE_GET_FIELD (~{}, 0)" c)))
			(def r (fn [c] (c* "DEFTYPE_GET_FIELD (~{}, 1)" c)))
			(let [c (Cons 1 2)]
			  (print (f c))
			  (print (r c)))))
	   [1 2]))))

;;(run-tests *ns*)