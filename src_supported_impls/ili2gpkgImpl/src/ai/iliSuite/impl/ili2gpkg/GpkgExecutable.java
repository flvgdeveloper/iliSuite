package ai.iliSuite.impl.ili2gpkg;
import java.util.List;

import ai.iliSuite.base.IliExecutable;
import ch.ehi.ili2gpkg.GpkgMain;

public class GpkgExecutable implements IliExecutable {

	@Override
	public void run(List<String> params) {
		String[] args = params.toArray(new String[0]);
		(new GpkgMain()).domain(args);
	}

}
