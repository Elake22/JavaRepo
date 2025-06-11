# Locker Manager Feedback

Overall-PASSED

Your separation of concerns is impressive for a beginner project. The five-class structure shows good understanding of single responsibility principle.

I have a minor design issue that there isn't a Locker class, right now you're storing the pin as the value for the locker, but that's not very extensible. If we wanted to add more information to the locker it would require significant refactoring and the introduction of a locker object.

However, this summative was more about basics than design. I like that you're thinking about separation of concerns. You're in a good place for this part of the training!