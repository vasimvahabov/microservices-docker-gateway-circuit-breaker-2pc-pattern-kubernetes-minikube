insert into authors_(first_name_,last_name_)
      values('Caroline','Arnold'),
            ('Randy','Pausch'),
            ('Gretchen','Rubin'),
            ('Tim','Ferriss'),
            ('Dale','Carnegie');

insert into types_(title_) 
      values('self-help'),
            ('non-fiction'),
            ('stunt'),
            ('non-fiction'),
            ('action-book');

insert into books_(name_,page_count_,author_id_,type_id_)
      values('Small Move, Big Change',272,1,1),
            ('The Last Lecture',206,2,2),
            ('The Happiness Project',368,3,3),
            ('The 4-Hour Workweek',448,2,2),
            ('How to Win Friends and Influence People',320,5,4);
