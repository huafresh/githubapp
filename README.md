## Basic introduction
This is a simple GitHub Android client. The home page displays the user's repository list, 
and the side drawer displays simple user information. App also supports login and repository search.
App page is designed with MVVM architecture. Network request is implemented with okhttp + retrofit, 
Data persistent storage is implemented with Room library, and the asynchronous framework is 
implemented with kotlin coroutine.
A layer of abstract encapsulation is made for image loading to facilitate subsequent 
switching of image loading framework.

## Directory introduction
db: Store database related codes, such as the definition of Entity and Dao
entity: Storage data entity class
ext: Store kotlin extension codes
http: Store the code related to the network request
image: Store image loading related codes
lib: Store custom frameworks. Each subdirectory corresponds to a framework. 
    For example, loadview corresponds to a framework for resolving view switching 
    such as loading / loading failure / no content.
login: Store login related codes
ui: Store UI codes
utils: Store various tool classes