/**
 * @class FunctionalInterface.java
 * @author Louis Wong
 */
package Controller;

/**
 * lambda expression is used at login & main screen for initialize the scene, since both main controller and login controller are shared the same method, but initiating different element,the lambda expression makes it convenient with abstract method, which can be defined differently with flexibility.
 */
public interface FunctionalInterface {
    void initializeLambda();
}
