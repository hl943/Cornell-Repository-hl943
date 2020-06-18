# -*- coding: utf-8 -*-
"""
Created on Sun Dec  1 23:40:15 2019

@author: guy
"""

import numpy as np
import pandas as pd
import datetime
import re
from sklearn.model_selection import train_test_split, ShuffleSplit, cross_val_score
from nltk.corpus import stopwords
from nltk.stem import PorterStemmer
import en_core_web_md
from sklearn.linear_model import LogisticRegression



# <codecell> Loading data from dataset


def read_files(train_file):
    df = pd.read_csv(train_file, index_col=0)
    df_X = df[df.columns[0:17]]
    Y = np.array(df['label'])
        
    return df_X, Y

df_X_train, Y_train = read_files('train.csv')


# <codecell> main program for training ML model
def train_and_classify(df_X_train, Y_train, df_X_test):

    # data prerocess and feature extraction       
    def extract_feature_vec(df_X_train, df_X_test):
        morning  =[]
        reply_to = []
        hashtags = []
        quoting = []
        link = []
        special_words = []
        signature = []
        fc = []
        rt = []
        
        # processing time data 
        for dt in df_X_train['created']:
            time_created = datetime.datetime.strptime(dt, \
                                                   '%m/%d/%Y %H:%M')
            #work.append((time_created.time().hour-12.0)/12.0 )
            
            hr = time_created.time().hour
            if ((hr>=0) & (hr<=5)):
                morning.append(0.0)
            if ((hr>5) & (hr<=12)):
                morning.append(1.0)
            if ((hr>12) & (hr<=18)):
                morning.append(0.0)
            else:
                morning.append(-0.4)
            
            
            '''
            if (hr>6 and hr<20):
                work.append(0.5)
            else:
                work.append(-1.0)
            if (time_created.time() < datetime.time(18,0) and time_created.time()> datetime.time(6,0)):
                work.append(1.0)
            elif (time_created.time() > datetime.time(18,0)):
                morning.append(-1.0)
            else:
                morning.append(0.0)
            '''
                
        # process favorited counts
        fc = df_X_train['favoriteCount']/(df_X_train['favoriteCount'].max()-df_X_train['favoriteCount'].min())
        rt = df_X_train['retweetCount']/(df_X_train['retweetCount'].max()-df_X_train['retweetCount'].min())
        '''
        sc = StandardScaler()
        fc = sc.fit_transform(np.ndarray(df_X_train['favoriteCount']).reshape(-1, 1))
        
        sc = StandardScaler()
        rt = sc.fit_transform(df_X_train['retweetCount'].reshape(-1, 1))
        '''
        ps = PorterStemmer();
        # hand picked features by Guy:
        for tweet_unf in df_X_train['text']:
            
            # remove what's in qoutations which is the words of other people
            tweet = re.sub(r'"(.*?)"', '', tweet_unf.lower())
            tweet = ps.stem(tweet)
            if '@' in tweet:
                reply_to.append(0.7)
            else:
                reply_to.append(0.0)
                
            if '#' in tweet:
                hashtags.append(-0.5)
            else:
                hashtags.append(0.0)
                
            if 'https' in tweet:
                link.append(0.7)
            else:
                link.append(-0.1)
            if tweet_unf.startswith('"'):
                quoting.append(0.5)
            else:
                quoting.append(0.0) #don't know
                
            if tweet_unf.endswith('Trump'):
                signature.append(0.5)
            else:
                signature.append(0.0) #don't know
                
            if any(re.findall(r'hate|Donald J. Trump|fake|lie|crooked|weak|stupid|mistake|Obama|Hillary|rigged', tweet, re.IGNORECASE)):
                special_words.append(0.8)
            elif any(re.findall(r'join|poll|thank', tweet, re.IGNORECASE)):
                special_words.append(-0.4)
            else:
                special_words.append(0.0)
        
        
        # processing special characters and numbers, this actually get rid of 
        # the hand pick features in below (such as @ and http etc,) so we will
        # run through the hand picked features first before we conduct text
        # embeddings
        tweet = df_X_train['text'].str.lower();
        tweet = tweet.apply(lambda x: re.sub("[^a-z\s]","",x));
        
        # remove stop wards
        sw = set(stopwords.words('english'));
        tweet = tweet.apply(lambda x: "  ".join(word for word in x.split() if word not in sw));
        
        
        # creating tokens using spacy
        nlp = en_core_web_md.load();
        document = nlp.pipe(tweet);
        tweet_vec = np.array([tweet.vector for tweet in document])
        
        # final training data set
        temp = np.vstack((morning, signature,special_words,reply_to,hashtags,quoting,link)); 
        #temp = np.vstack((work,fc,rt)); 

        x_trans_train = np.hstack((tweet_vec, temp.T))
        
        
        # same embeddings for teest data, embedding separately to prevent data leakage
#-------------------------------------------------------------------------------------------------------------------------#
        # initialize feature vectors for tweets
        morning_t  =[]
        reply_to_t = []
        hashtags_t = []
        quoting_t = []
        link_t = []
        special_words_t = []
        signature_t = []
        fc_t = []
        rt_t = []
        
                # processing time data 
        for dt in df_X_train['created']:
            time_created = datetime.datetime.strptime(dt, \
                                                   '%m/%d/%Y %H:%M')
            #work.append((time_created.time().hour-12.0)/12.0 )
            
            hr = time_created.time().hour
            if ((hr>=0) & (hr<=5)):
                morning_t.append(0.0)
            if ((hr>5) & (hr<=12)):
                morning_t.append(1.0)
            if ((hr>12) & (hr<=18)):
                morning_t.append(0.0)
            else:
                morning_t.append(-0.4)
                
        # processing time data 
        ps = PorterStemmer();
        for dt in df_X_test['created']:
            time_created = datetime.datetime.strptime(dt, \
                                                   '%m/%d/%Y %H:%M')
            work_t.append((time_created.time().hour-12.0)/12.0 )
            '''
            hr = time_created.time().hour
            if (hr>6 and hr<20):
                work_t.append(0.5)
            else:
                work_t.append(-1.0)
                
            if (time_created.time() < datetime.time(18,0) and time_created.time()> datetime.time(6,0)):
                work.append(1.0)
            elif (time_created.time() > datetime.time(18,0)):
                morning.append(-1.0)
            else:
                morning.append(0.0)
            '''
        # process favorited and retweet counts
        fc_t = df_X_test['favoriteCount']/(df_X_test['favoriteCount'].max()-df_X_test['favoriteCount'].min())
        rt_t = df_X_test['retweetCount']/(df_X_test['retweetCount'].max()-df_X_test['retweetCount'].min())
        '''
        sc = StandardScaler()
        fc_t = sc.fit_transform(df_X_test['favoriteCount'])
        sc = StandardScaler()
        rt_t = sc.fit_transform(df_X_test['retweetCount'])
        '''            
        # hand picked features by Guy:
        for tweet_unf in df_X_test['text']:
            # remove what's in qoutations which is the words of other people
            tweet = re.sub(r'"(.*?)"', '', tweet_unf.lower())
            tweet = ps.stem(tweet)
            
            if '@' in tweet:
                reply_to_t.append(0.7)
            else:
                reply_to_t.append(0.0)
                
            if '#' in tweet:
                hashtags_t.append(-0.5)
            else:
                hashtags_t.append(0.0)
                
            if 'https' in tweet:
                link_t.append(0.7)
            else:
                link_t.append(0.0)
            if tweet_unf.startswith('"'):
                quoting_t.append(0.5)
            else:
                quoting_t.append(0.0) #don't know
                
            if tweet_unf.endswith('Trump'):
                signature_t.append(0.5)
            else:
                signature_t.append(0.0) #don't know
                
            if any(re.findall(r'hate|Donald J. Trump|fake|lie|crooked|weak|stupid|mistake|Obama|Hillary|rigged', tweet, re.IGNORECASE)):
                special_words_t.append(0.8)
            elif any(re.findall(r'join|poll|thank', tweet, re.IGNORECASE)):
                special_words_t.append(-0.4)
            else:
                special_words_t.append(0.0)

        
        
        
        tweet_t = df_X_test['text'].str.lower();
        tweet_t = tweet_t.apply(lambda x: re.sub("[^a-z\s]","",x));
        
        # remove stop wards
        tweet_t = tweet_t.apply(lambda x: "  ".join(word for word in x.split() if word not in sw));
        
        
        # creating tokens using spacy
        document_t = nlp.pipe(tweet_t);
        tweet_vec_t = np.array([tweet_t.vector for tweet_t in document_t])
        temp_t = np.vstack((monring_t, signature_t,special_words_t,reply_to_t,hashtags_t,quoting_t,link_t)); 
        #temp_t = np.vstack((work_t,fc_t,rt_t)); 

        x_trans_test = np.hstack((tweet_vec_t, temp_t.T))
        
        return x_trans_train, x_trans_test
        #return tweet_vec, tweet_vec_t
        #return temp.T, temp_t.T

#-----------------------------------------------------------------------------------------------------------------------#


    
    
    def check_accuracy(Y_pred, Y_true):
        return (Y_pred == Y_true).sum() / Y_pred.shape[0]
    
 
    X_train,X_test = extract_feature_vec(df_X_train, df_X_test)
    # create and train model (consider doing k-fold cross validation as well)

    '''    
    Clist = [80, 82.5, 85, 87.5, 90] # SVM regularization parameter
    Glist = [1.05, 1.1, 1.15]
    best = {'acc': 0, 'C': 0, 'G': 0}
    for C in Clist:
        for gamma in Glist:
            xtr, xte, ytr, yte = train_test_split( \
                                    X_train, Y_train, test_size=0.2,\
                                    random_state=np.random.randint(0,100))
            model_svm = svm.SVC(kernel='rbf', gamma=gamma, C=C)
            #model_svm = svm.SVC(kernel='poly', degree=3, gamma='auto', C=C)
            clf = model_svm.fit(xtr, ytr)
            y = clf.predict(xte)
            acc = check_accuracy(y, yte)
            if(acc > best['acc']):
                best = {'acc': acc, 'C': C, 'G':gamma}
       
    # evaulate model on full data
    model_svm = svm.SVC(kernel='rbf', gamma=1.05, C=80)
    print(best['G']) 
    print(best['C'])
    model_svm = svm.SVC(kernel='poly', degree=3,gamma='auto',  C=best['C'])
    clf = model_svm.fit(X_train, Y_train)
    Y_test = clf.predict(X_test)
    '''
    
    # logistic regression with cross validation
    # cross validation with random shuffle
    LR = LogisticRegression(random_state=0, C = 1,solver='liblinear',penalty='l2')
    
    cv = ShuffleSplit(n_splits = 5, test_size = 0.3, random_state = 0)
    scores = cross_val_score(LR, X_train, Y_train, cv=cv)
    print('Cross-validation accuracy: ' + str(scores))
    print("Cross-validation accuracy: %0.2f (+/- %0.2f)" % (scores.mean(), scores.std()*2))
    clf = LR.fit(X_train, Y_train)
    Y_pred = clf.predict(X_test)
 
    
    '''
    text_classifier = RandomForestClassifier(n_estimators=150, random_state=0)
    clf = text_classifier.fit(X_train, y_train)
    Y_test = clf.predict(X_test)
    '''
    
    return Y_pred
    

# <codecell> evalulate and classify on training set
X_train, X_test, y_train, y_test = train_test_split( \
                                df_X_train, Y_train, test_size=0.4,\
                                random_state=np.random.randint(0,100))
Y_pred = train_and_classify(X_train, y_train, X_test)
#Y_pred = train_and_classify(df_X_train, Y_train, df_X_train)

def accuracy(Y_pred, Y_true):
    return (Y_pred == Y_true).sum() / Y_pred.shape[0]

acc = accuracy(Y_pred, y_test)
#acc = accuracy(Y_pred, Y_train)
print('accurary: ' + str(round(acc * 100, 2)) + '%')