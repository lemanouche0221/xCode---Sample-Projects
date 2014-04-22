use ExtUtils::MakeMaker;

require 5.010;

eval "use Test::Manifest 1.21";
	
# if we are running under Test::Prereq, we do not want side effects
my $TestPrereq = grep { m/Test.{1,2}Prereq.pm$/ } keys %INC;

unless( $TestPrereq )
	{
	open IN,  "mp3/iTunes_Music_Library";
	open OUT, "> mp3/iTunes Music Library";
	
	print OUT do { local $/; <IN> };

	open my($fh), "> t/test_manifest";
	print $fh <<"HERE";
load.t
pod.t
#prereq.t
check_mp3.t
itunes.t
item.t
playlist.t
endian.t
parse.t
version_check.t
#parse3.t
#parse4.t
#parse_audible.t
preferences.t
HERE
	
	close $fh;
	}

my $VarName = "NO_MAC";
my $Mac = $^O =~ m/Mac|darwin/;
$Mac = 0 if defined $ENV{$VarName} || -e $VarName;

if ( not $Mac )
	{
	print <<"HERE";
I do not think this is a Mac (or you set the environment
variable $VarName, or the file $VarName exists), so I will
not install the AppleScript module.  You will not be able to
use some of the features in Mac::iTunes, but since you are
not on a Mac, you probably do not care about those.
HERE

	open my($fh), "> NO_MAC";
	print $fh 1;
	close $fh;
	}
elsif( 0 and not $TestPrereq )
	{
	open my($fh), ">> t/test_manifest";
	print $fh <<"HERE";
setup_itunes.t
track_info.t
get_playlists.t
get_tracks.t
position.t
controller.t
HERE
	}
	
my @Mac_specific = $Mac ? qw(
	lib/AppleScript.pm $(INST_LIBDIR)/iTunes/AppleScript.pm
	) : ();

my @Mac_prereqs = $Mac ? qw(		
	Mac::AppleScript 0.03
	) : ();

my @Mac_docs = $Mac ? qw(
	lib/AppleScript.pm	$(INST_MAN3DIR)/Mac::iTunes::AppleScript.3
	) : ();
	
WriteMakefile(
    'NAME'          => 'Mac::iTunes',
	'ABSTRACT'      => 'interact with and control iTunes',
    'VERSION_FROM'  => 'lib/iTunes.pm',
	'LICENSE'       => 'perl',
	'AUTHOR'        => 'brian d foy <bdfoy@cpan.org>',
	
    'PM'            => {
    	@Mac_specific,
    	'lib/Item.pm'         => '$(INST_LIBDIR)/iTunes/Item.pm',
	   	'lib/iTunes.pm'       => '$(INST_LIBDIR)/iTunes.pm',
    	'lib/Parse.pm'        => '$(INST_LIBDIR)/iTunes/Library/Parse.pm',
    	'lib/Playlist.pm'     => '$(INST_LIBDIR)/iTunes/Playlist.pm',
    	'lib/Preferences.pm'  => '$(INST_LIBDIR)/iTunes/Preferences.pm',
    	'lib/Write.pm'        => '$(INST_LIBDIR)/iTunes/Library/Write.pm',
    	'doc/file_format.pod' => '$(INST_LIBDIR)/iTunes/FileFormat.pm',
    	},

	'PREREQ_PM' => {
		@Mac_prereqs,
		'Mac::Path::Util'   => '0.07',
		'Mac::PropertyList' => '0',
		'MP3::Info'         => '0',
		'Test::Manifest'    => '0.9',
		'Test::More'        => '0',
		'Test::Manifest'    => '1.14',
		},

	'MAN3PODS' =>
		{
		@Mac_docs,
		'lib/Item.pm'         => '$(INST_MAN3DIR)/Mac::iTunes::Item.3',
		'lib/iTunes.pm'       => '$(INST_MAN3DIR)/Mac::iTunes.3',
		'lib/Parse.pm'        => '$(INST_MAN3DIR)/Mac::iTunes::Parse.3',
		'lib/Playlist.pm'     => '$(INST_MAN3DIR)/Mac::iTunes::Playlist.3',
		'lib/Preferences.pm'  => '$(INST_MAN3DIR)/Mac::iTunes::Preferences.3',
		'lib/Write.pm'        => '$(INST_MAN3DIR)/Mac::iTunes::Write.3',
    	'doc/file_format.pod' => '$(INST_MAN3DIR)/Mac::iTunes::FileFormat.3',
		},
	    	
    clean  => { FILES      => q|Mac-iTunes-* mp3/iTunes\\ Music\\ Library t/test_manifest NO_MAC| },
	);
