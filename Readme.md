# Pokemon Assignment

**Changes made**
- I've create a network moduls which responsible for API calls.
- Create a dagger dependencies.
- Navigation was used.
- Create kotlin extensions for handling click events, SingleLiveEvent, Coroutines etc.
- I've added PokemonDetailsViewModel (unit testing for PokemonDetailsViewModel) & MainViewModelTest 
- I've also created CoroutineContextProvider which is responsible for injecting proper Coroutine dispatchers and TestCoroutineContextProvider its counterpart in unit test which also responsible for injecting proper coroutines dispatcher for unit testing. 
- I also created a BaseFragment and BaseFragmentViewHandler which responsible for handling fragment and its view separately, by doing this I've made sure that I won't make the fragment class over populated with codes.

**Thing/s should be add (if I'm required to add these features)**
- No network handling if the pokemon list is empty and the device not connected to the internet.
