FasdUAS 1.101.10   ��   ��    k             l     ��  ��    9 3 Created 2013-10-14 16:05:02 +1100 by Shane Stanley     � 	 	 f   C r e a t e d   2 0 1 3 - 1 0 - 1 4   1 6 : 0 5 : 0 2   + 1 1 0 0   b y   S h a n e   S t a n l e y   
  
 l     ��  ��      � 2013     �      �   2 0 1 3      x     ��  ��    1      ��
�� 
ascr  �� ��
�� 
minv  m         �    2 . 3��        x    �� ����    2  	 ��
�� 
osax��        x     �� ����    4    �� 
�� 
frmk  m       �    F o u n d a t i o n��         l     ��������  ��  ��      ! " ! l     �� # $��   # 4 . Example of file manager directory enumeration    $ � % % \   E x a m p l e   o f   f i l e   m a n a g e r   d i r e c t o r y   e n u m e r a t i o n "  &�� & i     # ' ( ' I      �� )���� 0 getpathsin_ getPathsIn_ )  *�� * o      ���� 0 
folderpath 
folderPath��  ��   ( k     V + +  , - , l     �� . /��   .  	 make URL    / � 0 0    m a k e   U R L -  1 2 1 r     
 3 4 3 n     5 6 5 I    �� 7���� $0 fileurlwithpath_ fileURLWithPath_ 7  8�� 8 o    ���� 0 
folderpath 
folderPath��  ��   6 n     9 : 9 o    ���� 0 nsurl NSURL : m     ��
�� misccura 4 o      ���� 0 theurl theURL 2  ; < ; l   �� = >��   =   make file manager    > � ? ? $   m a k e   f i l e   m a n a g e r <  @ A @ r     B C B n    D E D I    �������� 0 new  ��  ��   E n    F G F o    ���� 0 nsfilemanager NSFileManager G m    ��
�� misccura C o      ���� 0 fm   A  H I H l   �� J K��   J   get URL enumerator    K � L L &   g e t   U R L   e n u m e r a t o r I  M N M r    ! O P O n    Q R Q I    �� S���� �0 @enumeratoraturl_includingpropertiesforkeys_options_errorhandler_ @enumeratorAtURL_includingPropertiesForKeys_options_errorHandler_ S  T U T o    ���� 0 theurl theURL U  V W V J    ����   W  X Y X m    ����   Y  Z�� Z l    [���� [ m    ��
�� 
msng��  ��  ��  ��   R o    ���� 0 fm   P o      ���� 0 theenumerator theEnumerator N  \�� \ Y   " V ]�� ^ _�� ] l  , Q ` a b ` k   , Q c c  d e d l  , ,�� f g��   f !  get next URL in enumerator    g � h h 6   g e t   n e x t   U R L   i n   e n u m e r a t o r e  i j i r   , 3 k l k n  , 1 m n m I   - 1�������� 0 
nextobject 
nextObject��  ��   n o   , -���� 0 theenumerator theEnumerator l o      ���� 0 aurl aURL j  o p o l  4 ? q r s q Z  4 ? t u���� t =  4 7 v w v o   4 5���� 0 aurl aURL w m   5 6��
�� 
msng u  S   : ;��  ��   r , & missing value means there are no more    s � x x L   m i s s i n g   v a l u e   m e a n s   t h e r e   a r e   n o   m o r e p  y z y l  @ @�� { |��   {   log the URL's path    | � } } &   l o g   t h e   U R L ' s   p a t h z  ~  ~ I  @ O�� ���
�� .ascrcmnt****      � **** � l  @ K ����� � b   @ K � � � b   @ C � � � m   @ A � � � � � 
 P a t h   � o   A B���� 0 i   � l  C J ����� � c   C J � � � n  C H � � � I   D H�������� 0 path  ��  ��   � o   C D���� 0 aurl aURL � m   H I��
�� 
ctxt��  ��  ��  ��  ��     � � � l  P P�� � ���   � 
  or:    � � � �    o r : �  ��� � l  P P�� � ���   � } w current application's NSLog("Path %d: %@", i, aURL's |path|()) -- note %d required for integers with NSLog(), *not* %@    � � � � �   c u r r e n t   a p p l i c a t i o n ' s   N S L o g ( " P a t h   % d :   % @ " ,   i ,   a U R L ' s   | p a t h | ( ) )   - -   n o t e   % d   r e q u i r e d   f o r   i n t e g e r s   w i t h   N S L o g ( ) ,   * n o t *   % @��   a   arbitrary number    b � � � "   a r b i t r a r y   n u m b e r�� 0 i   ^ m   % &����  _ m   & '���� 2��  ��  ��       �� � � ���   � ����
�� 
pimr�� 0 getpathsin_ getPathsIn_ � �� ���  �   � � � � �� ��
�� 
vers��   � �� ���
�� 
cobj �  � �   ��
�� 
osax��   � �� ���
�� 
cobj �  � �   �� 
�� 
frmk��   � �� (���� � ����� 0 getpathsin_ getPathsIn_�� �� ���  �  ���� 0 
folderpath 
folderPath��   � �������������� 0 
folderpath 
folderPath�� 0 theurl theURL�� 0 fm  �� 0 theenumerator theEnumerator�� 0 i  �� 0 aurl aURL � �������������������� �������
�� misccura�� 0 nsurl NSURL�� $0 fileurlwithpath_ fileURLWithPath_�� 0 nsfilemanager NSFileManager�� 0 new  
�� 
msng�� �� �0 @enumeratoraturl_includingpropertiesforkeys_options_errorhandler_ @enumeratorAtURL_includingPropertiesForKeys_options_errorHandler_�� 2�� 0 
nextobject 
nextObject�� 0 path  
�� 
ctxt
�� .ascrcmnt****      � ****�� W��,�k+ E�O��,j+ E�O��jvj��+ E�O 3k�kh �j+ 	E�O��  Y hO�%�j+ �&%j OP[OY��ascr  ��ޭ